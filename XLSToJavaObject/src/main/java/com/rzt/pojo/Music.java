package com.rzt.pojo;

import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;

@ExcelObject( parseType = ParseType.ROW, start = 2, end = 3 )
public class Music {

	@ExcelField( position = 4 )
	private String type;
	@ExcelField( position = 5 )
	private String category;

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory( String category )
	{
		this.category = category;
	}

}
