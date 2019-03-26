package com.alibaba.check.service.impl;

import com.alibaba.check.common.MyException;
import com.alibaba.check.mapper.ImportCheckMapper;
import com.alibaba.check.pojo.AliCheck;
import com.alibaba.check.service.ImportCheckService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ImportCheckServiceImpl implements ImportCheckService{

    @Autowired
    private ImportCheckMapper importCheckMapper;

    /**
     * 导出
     * @return
     */
    @Override
    public List<AliCheck> selectImportCheck() {
        return importCheckMapper.selectImportCheck();
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
        List<AliCheck> userList = new ArrayList<>();
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
        AliCheck aliCheck;
        for (int r = 2; r <= sheet.getLastRowNum(); r++) {//r = 2 表示从第三行开始循环 如果你的第三行开始是数据
            Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
            if (row == null){
                continue;
            }

            aliCheck = new AliCheck();

            if( row.getCell(0).getCellType() !=1){//循环时，得到每一行的单元格进行判断
                throw new MyException("导入失败(第"+(r+1)+"行,用户名请设为文本格式)");
            }
            //第一个单元格
            String wbNumber = row.getCell(0).getStringCellValue();//得到每一行第一个单元格的值

            if(wbNumber == null || wbNumber.isEmpty()){//判断是否为空
                throw new MyException("导入失败(第"+(r+1)+"行，工号未填写)");
            }

            String workNumber = row.getCell(1).getStringCellValue();//得到每一行第一个单元格的值

            if(workNumber == null || workNumber.isEmpty()){//判断是否为空
                throw new MyException("导入失败(第"+(r+1)+"行，工号未填写)");
            }

            //第三个单元格
            Date workTime;

            if(row.getCell(2).getCellType() !=0){
                throw new MyException("导入失败(第"+(r+1)+"行,导入日期格式不正确或未填写)");
            }else{
                workTime = row.getCell(2).getDateCellValue();
            }
            //第四个单元格
            Date upTime;

            if(row.getCell(3).getCellType() !=0){
                throw new MyException("导入失败(第"+(r+1)+"行,导入日期格式不正确或未填写)");
            }else{
                upTime = row.getCell(3).getDateCellValue();
            }

            //第五个单元格
            Date downTime;
            if(row.getCell(4).getCellType() !=0){
                throw new MyException("导入失败(第"+(r+1)+"行,导入日期格式不正确或未填写)");
            }else{
                downTime = row.getCell(4).getDateCellValue();
            }

            //完整的循环一次 就组成了一个对象
            aliCheck.setWbNumber(wbNumber);
            aliCheck.setWorkNumber(workNumber);
            aliCheck.setWorkTime(workTime);
            aliCheck.setUpTime(upTime);
            aliCheck.setDownTime(downTime);
            userList.add(aliCheck);
        }

        for (AliCheck userResord : userList) {

           Date up = userResord.getUpTime();//上班打卡时间
           Date down = userResord.getDownTime();//下班打卡时间
           Date upwork = userResord.getWorkTime();//上班规定时间
           Integer i = up.compareTo(upwork);//上班时间小于规定出勤时间
            //根据上班打卡时间获取下班时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(up);
            calendar.add(Calendar.HOUR,9);
            Date downwork = calendar.getTime();//
            Integer i1 = downwork.compareTo(down);
            //System.out.println("根据上班时间求下班时间=============="+downwork);
            // 0 正常  1 病假  2 事假  3迟到 4 早退  5矿工
           if (i<=0){//-1
               userResord.setWorkState("0");//正常
               if(i1<=0){
                   userResord.setWorkState("0");//正常
               }else {
                   userResord.setWorkState("4");//早退
               }
           }else{//1
               userResord.setWorkState("3");//迟到
           }
            //System.out.println("======上班时间：-1：正常  0：正常 1：异常========"+i);

           importCheckMapper.addUser(userResord);
            System.out.println(" 插入 "+userResord);
        }
        return notNull;
    }
}
