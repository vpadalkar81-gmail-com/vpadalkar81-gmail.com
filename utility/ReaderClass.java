package com.clover.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReaderClass {

	public static	HSSFWorkbook wb;
	public static HSSFSheet sheet;
	public static HSSFCell cell;
	public static Properties prop= new Properties();
	public static void ReadExcelFile(String excelPath) {
		try {
			File file=new File(excelPath);					
			FileInputStream fis=new FileInputStream(file);	
			wb=new HSSFWorkbook(fis);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	
	public static Object GetData(int SheetNumber,int row,int column) {
		String data;
		sheet=wb.getSheetAt(SheetNumber);
		try {
			cell=sheet.getRow(row).getCell(column);	
			data=cell.getStringCellValue();
		}catch(NullPointerException e) {
			data="";
		}
		//if (data==null) data="1";
	//	System.out.println("data"+data);
		return data;
	}
	
	/*
	 * public int GetRowCount(int sheetIndex) { //String sheetName int
	 * row=wb.getSheetAt(sheetIndex).getLastRowNum(); //int
	 * row=wb.getSheet(sheetName).getLastRowNum(); row+=1; return row;
	 * 
	 * }
	 */

	public static Properties getPropertyFile() throws IOException {
		//vai
		FileInputStream objfis=new FileInputStream(System.getProperty("user.dir")+"//PropertyFile//LoginProp.properties");
		prop.load(objfis);
		return prop;
		
	}
	
	
	
}

