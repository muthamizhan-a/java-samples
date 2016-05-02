package com.rzt.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.rzt.utils.ObjectHelper;
import com.rzt.utils.XLSHelper;

public class ObjectToXLS {

	public static void generateXLSSheet( List<Object> objects, String filePath, String headerColor, String fontColor )
			throws IOException
	{
		if( objects == null || filePath == null )
		{
			System.out.println("could not perform, please provide valid input");
			return;
		}
		if( objects.size() < 1 )
		{
			return;
		}
		System.out.println("-----Processing----");
		FileOutputStream outFile = null;

		try
		{
			List<String> attributes = ObjectHelper.getDeclaredAttributes(objects.get(0));

			XSSFWorkbook workbook = XLSHelper.createWorkBook();
			XSSFSheet sheet = XLSHelper.createSheet(workbook, "TestSheet");
			Row row;
			int rowNo = 0;
			row = sheet.createRow(rowNo++);
			for( int i = 0; i < attributes.size(); i++ )
			{
				XLSHelper.createCell(row, i, "" + attributes.get(i).toUpperCase(), headerColor);
			}

			for( Object obj : objects )
			{
				row = sheet.createRow(rowNo++);
				int cellNo = 0;
				for( String attrName : attributes )
				{
					XLSHelper.createCell(row, cellNo++, "" + ObjectHelper.getAttributeValue(obj, attrName), fontColor);
				}
			}

			outFile = new FileOutputStream(new File(filePath));
			workbook.write(outFile);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			outFile.close();
		}
	}

}
