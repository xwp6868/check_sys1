package com.alibaba.check.service.impl;

import com.alibaba.check.common.MyException;
import com.alibaba.check.mapper.ImportStaffMapper;
import com.alibaba.check.pojo.AliStaff;
import com.alibaba.check.service.ImportStaffService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ImportStaffServiceImpl implements ImportStaffService {

    @Autowired
    private ImportStaffMapper importStaffMapper;

    /**
     * 导出
     * @return
     */
    @Override
    public List<AliStaff> selectImportStaff() {

        return importStaffMapper.selectImportStaff();
    }

    /**
     * 导入
     * @param fileName
     * @param file
     * @return
     * @throws IOException
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<AliStaff> userList = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        AliStaff aliStaff;
        for (int r = 2; r <= sheet.getLastRowNum(); r++) {//r = 2 表示从第三行开始循环 如果你的第三行开始是数据
            Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
            if (row == null){
                continue;
            }

            aliStaff = new AliStaff();

            if( row.getCell(0).getCellType() !=1){//循环时，得到每一行的单元格进行判断
                throw new MyException("导入失败(第"+(r+1)+"行,用户名请设为文本格式)");
            }
            //第一个单元格
            String workNumber = row.getCell(0).getStringCellValue();//得到每一行第一个单元格的值

            if(workNumber == null || workNumber.isEmpty()){//判断是否为空
                throw new MyException("导入失败(第"+(r+1)+"行，工号未填写)");
            }

            //第二个单元格
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值

            String name = row.getCell(1).getStringCellValue();

            if(name==null || name.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,姓名未填写)");
            }

            //第三个单元格
            Date entryTime;

            if(row.getCell(2).getCellType() !=0){
                throw new MyException("导入失败(第"+(r+1)+"行,导入日期格式不正确或未填写)");
            }else{
                entryTime = row.getCell(2).getDateCellValue();
            }

            //完整的循环一次 就组成了一个对象
            aliStaff.setWorkNumber(workNumber);
            aliStaff.setName(name);
            aliStaff.setEntryTime(entryTime);
            userList.add(aliStaff);
        }
        for (AliStaff userResord : userList) {
            String name = userResord.getWorkNumber();
            //根据工号判断，去除重复（工号相同进行更新，工号不同则进行添加）
            int cnt = importStaffMapper.selectByName(name);
            if (cnt == 0 ) {
                importStaffMapper.addUser(userResord);
                System.out.println(" 插入 "+userResord);
            } else {
                importStaffMapper.updateUserByName(userResord);
                System.out.println(" 更新 "+userResord);
            }
        }
        return notNull;
    }

}
