package com.rzt;

import java.util.ArrayList;

public class AppMain {

	public static void main( String[] args )
	{
		ArrayList<Integer> input = new ArrayList<Integer>();
		input.add(7);
		input.add(10);
		input.add(8);
		input.add(11);
		input.add(9);
		input.add(12);

		System.out.println("-------------->" + NumberSeriesSolver.predictNumber(input));

	}

}
