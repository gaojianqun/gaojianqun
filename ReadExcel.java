package com.qitoon.app.cms.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qitoon.app.cms.api.dao.entity.CmsLibContent;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gaojianqun on 2018/1/26.
 */
public class ReadExcel {

    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;}
    //获取总列数
    public int getTotalCells() {  return totalCells;}
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }

    /**
     * 读EXCEL文件，获取信息集合
     * @param mFile
     * @return
     */
    public String getExcelInfo(MultipartFile mFile) {
        String jsonStr = "";
        String fileName = mFile.getOriginalFilename();//获取文件名
        List<CmsLibContent> cmsLibContent = new ArrayList<>();
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2013 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2017(fileName)) {
                isExcel2013 = false;
            }
            jsonStr = createExcel(mFile.getInputStream(), isExcel2013);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    /**
     * 根据excel里面的内容读取客户信息
     * @param is 输入流
     * @param isExcel2013 excel是2003还是2007版本
     * @return
     */
    public String createExcel(InputStream is, boolean isExcel2013) {
        List<CmsLibContent> cmsLibContent = new ArrayList<>();
        String jsonStr = "";
        try{
            Workbook wb = null;
            if (isExcel2013) {// 当excel是2013时,创建excel2013
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2017时,创建excel2017
                wb = new XSSFWorkbook(is);
            }
             jsonStr = readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private String readExcelValue(Workbook wb) throws Exception{
        String result = "";
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<CmsLibContent> list = new ArrayList<CmsLibContent>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            CmsLibContent cmsLibContent = new CmsLibContent();
            // 循环Excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (cell != null) {
                    if (c == 0) {
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String title = String.valueOf(cell.getNumericCellValue());
                            cmsLibContent.setTitle(title.substring(0, title.length()-2>0?title.length()-2:1));//标题
                        }else{
                            if(cell.getStringCellValue() !=null && !"".equals(cell.getStringCellValue())){
                                cmsLibContent.setTitle(cell.getStringCellValue());//标题
                            }else{
                                return "文件格式不正确，请检查标题、正文字段是否为空";
                            }
                        }
                    } else if (c == 1) {
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String author = String.valueOf(cell.getNumericCellValue());
                            cmsLibContent.setAuthor(author.substring(0, author.length()-2>0?author.length()-2:1));//作者
                        }else{
                            cmsLibContent.setAuthor(cell.getStringCellValue());//作者
                        }
                    } else if (c == 2){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String source = String.valueOf(cell.getNumericCellValue());
                            cmsLibContent.setSource(source.substring(0, source.length()-2>0?source.length()-2:1));//来源
                        }else{
                            cmsLibContent.setSource(cell.getStringCellValue());//来源
                        }
                    }else if (c == 3){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String publishTime = String.valueOf(cell.getNumericCellValue());
                            Date dateTime1 = formatter.parse(publishTime.substring(0, publishTime.length()-2>0?publishTime.length()-2:1));
                            cmsLibContent.setPublishTime(dateTime1);//发布时间
                        }else{
                            Date dateTime2 = formatter.parse(cell.getStringCellValue());
                            cmsLibContent.setPublishTime(dateTime2);//发布时间
                        }
                    }else if (c == 4){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String inArea = String.valueOf(cell.getNumericCellValue());
                            cmsLibContent.setCmsContent(inArea.substring(0, inArea.length()-2>0?inArea.length()-2:1));//正文内容
                        }else{
                            if(cell.getStringCellValue() !=null && !"".equals(cell.getStringCellValue())){
                                cmsLibContent.setCmsContent(cell.getStringCellValue());//正文内容
                            }else{
                                return "文件格式不正确，请检查标题、正文字段是否为空";
                            }
                        }
                    }

                }else{
                    return "文件格式不正确，请检查标题、正文字段是否为空";
                }
            }
            // 添加到list
            list.add(cmsLibContent);
            result = JSONObject.toJSONString(list);
        }
        return result;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2013(filePath) || isExcel2017(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2013(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2017(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

}
