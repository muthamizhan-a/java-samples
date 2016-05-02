package com.rzt;

import java.util.ArrayList;

public class NumberSeriesSolver {

	static ArrayList<Integer> tempList = new ArrayList<Integer>();
	static ArrayList<Integer> sumList = new ArrayList<Integer>();

	public static Integer predictNumber( ArrayList<Integer> inputList )
	{
		if( inputList == null || inputList.isEmpty() )
			return null;
		sumList.add(inputList.get(inputList.size() - 1));
		for( int i = 0; i < inputList.size() - 1; i++ )
		{
			int res = inputList.get(i + 1) - inputList.get(i);
			tempList.add(res);

		}
		if( !isTempContainsSameValues() )
		{
			inputList.clear();
			inputList.addAll(tempList);
			tempList.clear();
			return predictNumber(inputList);
		}
		else
		{
			int result = tempList.get(0);
			for( int val : sumList )
			{
				result += val;
			}
			return result;
		}

	}

	public static boolean isTempContainsSameValues()
	{
		int matchVal = tempList.get(0);
		for( int val : tempList )
		{
			if( val != matchVal )
				return false;
		}
		return true;
	}

}
