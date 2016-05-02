package com.rzt.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.javafunk.excelparser.SheetParser;

public class XLSToJavaObject {

	@SuppressWarnings( "deprecation" )
	public static List<?> getObjectsForXLSSheet( String filePath, String sheetName, Object obj )
	{
		if( filePath == null || sheetName == null )
		{

			return null;
		}
		try
		{
			SheetParser parser = new SheetParser();
			InputStream inputStream = new FileInputStream(new File(filePath));
			Sheet sheet = new HSSFWorkbook(inputStream).getSheet(sheetName);

			//Invoking the Sheet parser.
			return parser.createEntity(sheet, sheetName, obj.getClass());

		}
		catch( Exception e )
		{
			e.printStackTrace();
			return null;
		}
	}

}
