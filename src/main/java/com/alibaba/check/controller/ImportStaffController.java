package com.alibaba.check.controller;


import com.alibaba.check.pojo.AliStaff;
import com.alibaba.check.service.ImportStaffService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 员工信息导入导出
 */
@CrossOrigin(origins = "*",
        maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class ImportStaffController {

    @Autowired
    private ImportStaffService importStaffService;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String showStaff(Model model){
        List<AliStaff> aliStaffs = importStaffService.selectImportStaff();
        model.addAttribute("aliStaffs",aliStaffs);
        return "index";
    }

    /**
     * 导出
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletResponse response) throws IOException {
        List<AliStaff> aliStaffs = importStaffService.selectImportStaff();

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("员工信息表格");

        HSSFRow row = null;

        row = sheet.createRow(0);//创建第一个单元格
        row.setHeight((short) (26.25 * 20));
        row.createCell(0).setCellValue("员工管理");//为第一行单元格设值

        /*为标题设计空间
         * firstRow从第1行开始
         * lastRow从第0行结束
         *
         *从第1个单元格开始
         * 从第3个单元格结束
         */
        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
        sheet.addMergedRegion(rowRegion);

		/*CellRangeAddress columnRegion = new CellRangeAddress(1,4,0,0);
		sheet.addMergedRegion(columnRegion);*/


        /*
         * 动态获取数据库列 sql语句 select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name='user' and table_schema='test'
         * 第一个table_name 表名字
         * 第二个table_name 数据库名称
         * */
        row = sheet.createRow(1);
        row.setHeight((short) (22.50 * 20));//设置行高
        row.createCell(0).setCellValue("工号");//为第一个单元格设值
        row.createCell(1).setCellValue("姓名");//为第二个单元格设值
        row.createCell(2).setCellValue("录入日期");//为第三个单元格设值

        for (int i = 0; i < aliStaffs.size(); i++) {
            row = sheet.createRow(i + 2);
            AliStaff aliStaff = aliStaffs.get(i);
            row.createCell(0).setCellValue(aliStaff.getWorkNumber());
            row.createCell(1).setCellValue(aliStaff.getName());
            // 日期格式转为字符串输出
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time= sdf.format(aliStaff.getEntryTime());
            row.createCell(2).setCellValue(time);


        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
        for (int i = 0; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=staff.xls");//默认Excel名称
        wb.write(os);
        os.flush();
        os.close();


    }

    /**
     * 导入
     * @param file
     * @param session
     * @return
     */
    @RequestMapping(value = "/import")
    public String exImport(@RequestParam(value = "filename")MultipartFile file, HttpSession session) {

        boolean a = false;

        String fileName = file.getOriginalFilename();

        try {
            a = importStaffService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:index";
    }

    /**
     * 获取样式
     *
     * @param hssfWorkbook
     * @param styleNum
     * @return
     */
    public HSSFCellStyle getStyle(HSSFWorkbook hssfWorkbook, Integer styleNum) {
        HSSFCellStyle style = hssfWorkbook.createCellStyle();
        style.setBorderRight(BorderStyle.THIN);//右边框
        style.setBorderBottom(BorderStyle.THIN);//下边框

        HSSFFont font = hssfWorkbook.createFont();
        font.setFontName("微软雅黑");//设置字体为微软雅黑

        HSSFPalette palette = hssfWorkbook.getCustomPalette();//拿到palette颜色板,可以根据需要设置颜色
        switch (styleNum) {
            case (0): {//HorizontalAlignment
                style.setAlignment(HorizontalAlignment.CENTER_SELECTION);//跨列居中
                font.setBold(true);//粗体
                font.setFontHeightInPoints((short) 14);//字体大小
                style.setFont(font);
                palette.setColorAtIndex(HSSFColor.BLUE.index, (byte) 184, (byte) 204, (byte) 228);//替换颜色板中的颜色
                style.setFillForegroundColor(HSSFColor.BLUE.index);
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            }
            break;
            case (1): {
                font.setBold(true);//粗体
                font.setFontHeightInPoints((short) 11);//字体大小
                style.setFont(font);
            }
            break;
            case (2): {
                font.setFontHeightInPoints((short) 10);
                style.setFont(font);
            }
            break;
            case (3): {
                style.setFont(font);

                palette.setColorAtIndex(HSSFColor.GREEN.index, (byte) 0, (byte) 32, (byte) 96);//替换颜色板中的颜色
                style.setFillForegroundColor(HSSFColor.GREEN.index);
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            }
            break;
        }

        return style;
    }

}
