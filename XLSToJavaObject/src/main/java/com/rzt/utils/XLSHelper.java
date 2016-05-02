package com.rzt.utils;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSHelper {

	static HashMap<XSSFWorkbook, XSSFCellStyle[]> workbookToStyles = new HashMap<XSSFWorkbook, XSSFCellStyle[]>();
	static XSSFCellStyle[] styles = new XSSFCellStyle[3];

	public static XSSFSheet createSheet( XSSFWorkbook workBook, String sheetName )
	{
		XSSFSheet sheet = workBook.getSheet(sheetName);
		if( sheet == null )
		{
			workBook.createSheet(sheetName);
		}
		removeExistingRows(workBook.getSheet(sheetName));
		return workBook.getSheet(sheetName);
	}

	public static void removeExistingRows( XSSFSheet sheet )
	{
		ArrayList<Row> rows = new ArrayList<Row>();

		for( Row row : sheet )
		{
			rows.add(row);
		}

		for( Row row : rows )
		{
			sheet.removeRow(row);
		}
		rows.clear();
	}

	public static XSSFWorkbook createWorkBook()
	{
		try
		{
			XSSFWorkbook workbook = new XSSFWorkbook();
			styles[0] = createStyle(workbook, (short) -1);
			styles[1] = createStyle(workbook, HSSFColor.YELLOW.index);
			styles[2] = createStyle(workbook, HSSFColor.LIGHT_GREEN.index);
			workbookToStyles.put(workbook, styles);
			return workbook;
		}
		catch( Exception e )
		{
			e.printStackTrace();
			return null;
		}

	}

	public static XSSFCellStyle createStyle( XSSFWorkbook workbook, short color )
	{
		XSSFCellStyle style = workbook.createCellStyle();
		if( color > 0 )
		{
			style.setFillForegroundColor(color);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		}
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		XSSFFont hSSFFont = workbook.createFont();
		hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
		hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		style.setFont(hSSFFont);
		return style;

	}

	public static void createCell( Row row, int cellNo, Object val, String color )
	{
		XSSFCellStyle style = styles[0];

		if( color.toString().contains("yello") )
		{
			style = styles[1];
		}

		if( color.toString().contains("green") )
		{
			style = styles[2];
		}

		Cell cell = row.createCell(cellNo);
		try
		{
			Double d = new Double(val.toString());
			cell.setCellValue(d);
		}
		catch( Exception e )
		{
			cell.setCellValue(val.toString());
		}
		cell.setCellStyle(style);
	}

}
