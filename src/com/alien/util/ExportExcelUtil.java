package com.alien.util;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.Region;

/**
 * 工具类  ——  Excel 
 */

public class ExportExcelUtil {
	
	private HSSFWorkbook wb = null;   //EXCEL工具薄
	private HSSFSheet sheet = null;   //工作表
	
 
	public ExportExcelUtil(HSSFWorkbook wb, HSSFSheet sheet){
		super();
		this.wb = wb;
		this.sheet = sheet;
	}
	
	
	public HSSFWorkbook getWb() {
		return wb;
	} 
	
	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	} 

	public HSSFSheet getSheet() {
		return sheet;
	}
 
	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}


	/**  
     * 创建通用EXCEL头部  
     *   
     * @param headString  
     *            头部显示的字符  
     * @param colSum  
     *            该报表的列数  
     */ 
    public void createNormalHead(String headString, int colSum) {  
 
        HSSFRow row = sheet.createRow(0);  
 
        // 设置第一行  
        HSSFCell cell = row.createCell(0);  
        row.setHeight((short) 400);  
 
        // 定义单元格为字符串类型  
        cell.setCellType(HSSFCell.ENCODING_UTF_16);  
        cell.setCellValue(new HSSFRichTextString(headString));  
 
        // 指定合并区域  
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) colSum));  
 
        HSSFCellStyle cellStyle = wb.createCellStyle();  
 
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐  
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐  
        cellStyle.setWrapText(true);// 指定单元格自动换行  
 
        // 设置单元格字体  
        HSSFFont font = wb.createFont();  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        font.setFontName("宋体");  
        font.setFontHeight((short) 300);  
        cellStyle.setFont(font);  
 
        cell.setCellStyle(cellStyle);  
    }  
    
    
    /**  
     * 创建通用报表第二行  
     *   
     * @param params  
     *            统计条件数组  
     * @param colSum  
     *            需要合并到的列索引  
     */ 
    public void createNormalTwoRow(String[] params, int colSum) {  
        HSSFRow row = sheet.createRow(1);  
        row.setHeight((short) 300);  
 
        HSSFCell cell2 = row.createCell(0);  
 
        cell2.setCellType(HSSFCell.ENCODING_UTF_16);
        StringBuffer buff = new StringBuffer();
        for(int i=0;i<params.length;i++){
        	if(i%2==0 && i!=0){
        		buff.append("  ");
        	} 
        	buff.append(params[i]); 
        }
        cell2.setCellValue(new HSSFRichTextString(buff.toString()));  
 
        // 指定合并区域  
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) colSum));  
 
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐  
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐  
        cellStyle.setWrapText(true);// 指定单元格自动换行  
 
        // 设置单元格字体  
        HSSFFont font = wb.createFont();  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        font.setFontName("宋体");  
        font.setFontHeight((short) 250);  
        cellStyle.setFont(font);  
 
        cell2.setCellStyle(cellStyle);  
 
    } 
    
    
    /**  
     * 设置报表标题  
     *   
     * @param columHeader  
     *            标题字符串数组  
     */ 
    public void createColumHeader(String[] columHeader,int num) {  
 
        // 设置列头  
        HSSFRow row2 = sheet.createRow(num);  
 
        // 指定行高  
        row2.setHeight((short) 600);  
 
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐  
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐  
        cellStyle.setWrapText(true);// 指定单元格自动换行  
 
        // 单元格字体  
        HSSFFont font = wb.createFont();  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        font.setFontName("宋体");  
        font.setFontHeight((short) 250);  
        cellStyle.setFont(font);  
 
        /*  
         * cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单无格的边框为粗体  
         * cellStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．  
         * cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
         * cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);  
         * cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
         * cellStyle.setRightBorderColor(HSSFColor.BLACK.index);  
         * cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
         * cellStyle.setTopBorderColor(HSSFColor.BLACK.index);  
         */ 
 
        // 设置单元格背景色  
        cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);  
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
 
        HSSFCell cell3 = null;  
 
        for (int i = 0; i < columHeader.length; i++) {  
            cell3 = row2.createCell(i);  
            cell3.setCellType(HSSFCell.ENCODING_UTF_16);  
            cell3.setCellStyle(cellStyle);  
            cell3.setCellValue(new HSSFRichTextString(columHeader[i]));  
        }  
 
    }  
    
    /**  
     * 创建内容单元格  
     *   
     * @param wb  
     *            HSSFWorkbook  
     * @param row  
     *            HSSFRow  
     * @param col  
     *            short型的列索引  
     * @param align  
     *            对齐方式  
     * @param val  
     *            列值  
     */ 
    public void cteateCell(HSSFWorkbook wb, HSSFRow row, int col, short align,  
            String val,HSSFFont font) {  
        HSSFCell cell = row.createCell(col);  
        cell.setCellType(HSSFCell.ENCODING_UTF_16);  
        cell.setCellValue(new HSSFRichTextString(val));  
        HSSFCellStyle cellstyle = wb.createCellStyle();  
        cellstyle.setAlignment(align);   
        if(font!=null){
        	cellstyle.setFont(font);
        }
        cell.setCellStyle(cellstyle);  
    }
    
    
    /**  
     * 创建合计行  
     *   
     * @param colSum  
     *            需要合并到的列索引  
     * @param cellValue  
     */ 
    public void createLastSumRow(int colSum, String[] cellValue) {  
 
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐  
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐  
        cellStyle.setWrapText(true);// 指定单元格自动换行  
 
        // 单元格字体  
        HSSFFont font = wb.createFont();  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        font.setFontName("宋体");  
        font.setFontHeight((short) 250);  
        cellStyle.setFont(font);  
 
        HSSFRow lastRow = sheet.createRow((short) (sheet.getLastRowNum() + 1));  
        HSSFCell sumCell = lastRow.createCell(0);  
 
        sumCell.setCellValue(new HSSFRichTextString("合计"));  
        sumCell.setCellStyle(cellStyle);  
       
        sheet.addMergedRegion(new Region(sheet.getLastRowNum(), (short) 0,  
                sheet.getLastRowNum(), (short) colSum));// 指定合并区域  
 
        for (int i = 2; i < (cellValue.length + 2); i++) {  
            sumCell = lastRow.createCell(i);  
            sumCell.setCellStyle(cellStyle);  
            sumCell.setCellValue(new HSSFRichTextString(cellValue[i - 2]));  
 
        }  
 
    }  
     
    
	/**
	 * @功能：手工构建一个简单格式的Excel
	 */
	private  List<String> getString() throws Exception
	{
		
		List list = new ArrayList<String>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");

		String user1 = "张三";
		String user2 = "李四";
		String user3 = "王五";
		list.add(user1);
		list.add(user2);
		list.add(user3);

		return list;
	}
	
	 
	 
   public static void main(String[] args) { 
		
//		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//		HSSFRow row = exp.sheet.createRow((int) 3);
//		// 第四步，创建单元格，并设置值表头 设置表头居中
//		HSSFCellStyle style = exp.wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
//
//		HSSFCell cell = row.createCell((short) 0);
		 
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wbb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheetb = wbb.createSheet("学生表一"); 
		ExportExcelUtil exp = new ExportExcelUtil(wbb, sheetb);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		List list = null;
		try {
			list = exp.getString();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 // 计算该报表的列数  
        int number = 2 + list.size() * 2;
        
		exp.createNormalHead("互动——投票统计", number);
		String[] str = {"开始时间:","2012-12-12","结束时间:","2013-04-12"};
		exp.createNormalTwoRow(str, number);
		String[] strs = {"学号","姓名","年龄"};
		exp.createColumHeader(strs,2);
		
		
        
        // 循环创建中间的单元格的各项的值  
        for (int i = 5; i < number; i++) {  
            HSSFRow row1 = exp.sheet.createRow((short) i);   
            for (int j = 0; j <= number; j++) {  
                exp.cteateCell(wbb, row1, (short) j,  
                                HSSFCellStyle.ALIGN_CENTER_SELECTION, String  
                                        .valueOf(j),null);  
            }  
 
        }  
        
//		for (int i = 1; i < list.size(); i++)
//		{
//			row = exp.sheet.createRow((int) i + 2); 
//			// 第四步，创建单元格，并设置值
//			row.createCell((short) 0).setCellValue((double) i);
//			row.createCell((short) 1).setCellValue("张三");
//			row.createCell((short) 2).setCellValue((double) i);
//			cell = row.createCell((short) 3); 
//		}
		// 第六步，将文件存到指定位置
        
     // 创建最后一行的合计行  
        String[] cellValue = new String[number - 1];  
        for (int i = 0; i < number - 1; i++) {  
            cellValue[i] = String.valueOf(i);  
 
        }  
        exp.createLastSumRow(1, cellValue);  
//      exp.getSheet().autoSizeColumn((short) 0); 自动适配宽度
		try
		{
			FileOutputStream fout = new FileOutputStream("E:/students1.xls");
			exp.wb.write(fout);
			fout.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
}
 
}
