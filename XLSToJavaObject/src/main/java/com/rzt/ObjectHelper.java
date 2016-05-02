package com.rzt.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.reflect.FieldUtils;
import com.google.gson.Gson;

public class ObjectHelper {

	public static List<String> getDeclaredAttributes( Object object )
	{
		if( object == null )
		{
			System.out.println("ERROR: input is null");
			return null;
		}
		List<String> attributes = new ArrayList<String>();
		Field[] subFields = object.getClass().getDeclaredFields();
		Field[] supperClassFields = object.getClass().getSuperclass().getDeclaredFields();
		for( Field field : subFields )
		{
			attributes.add(field.getName());
		}
		for( Field field : supperClassFields )
		{
			attributes.add(field.getName());
		}
		return attributes;
	}

	public static Object getAttributeValue( Object obj, String variableName ) throws Exception
	{
		Field f;
		try
		{
			try
			{
				f = obj.getClass().getDeclaredField(variableName);
				f.setAccessible(true);
				return (String) f.get(obj);
			}
			catch( Exception e )
			{
				f = obj.getClass().getSuperclass().getDeclaredField(variableName);
				f.setAccessible(true);
				return (String) f.get(obj);
			}
		}
		catch( Exception e )
		{
			return FieldUtils.readField(obj, variableName, true);
		}

	}

	public static String parseObject( Object obj )
	{
		if( obj == null )
			return null;
		return new Gson().toJson(obj);
	}

}
