package com.rzt.main;

import com.rzt.systemMonitor.service.SystemMonitorUsingAMQ;

public class AppMain {

	public static void main( String[] args )
	{
		try
		{
			//new SystemMonitorUsingSocket("muthamizhan@razorthink.net", "tamizhgoogle").run();
			new SystemMonitorUsingAMQ("localhost", "61616", "REQUEST", "RESPONCE", "muthamizhan@razorthink.net",
					"tamizhgoogle").start();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

}
