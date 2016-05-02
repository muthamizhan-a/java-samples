package com.rzt.systemMonitor.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import com.rzt.systemMonitor.util.SystemMonitorUtil;

public class SystemMonitorUsingSocket extends Thread {

	private String senderMailId = "";
	private String senderPassword = "";

	public SystemMonitorUsingSocket( String senderMailId, String senderPassword )
	{
		this.senderMailId = senderMailId;
		this.senderPassword = senderPassword;

	}

	public void run()
	{
		try
		{
			startSocketServer();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

	}

	private void startSocketServer()
	{
		try
		{
			ServerSocket server = new ServerSocket(1234);
			System.out.println("Socket server started listening with :" + server.getLocalSocketAddress());
			while( true )
			{
				// Socket socket = server.accept();
				try( Socket socket = server.accept() )

				{

					BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String requestString = inFromClient.readLine();
					while( inFromClient.ready() )
					{
						requestString = inFromClient.readLine();
					}
					System.out.println("request came-----");
					PrintWriter out = new PrintWriter(socket.getOutputStream());
					if( requestString != null && SystemMonitorUtil.isValidMailId(requestString.trim()) )
					{
						//starting mailer service.
						new MailSender(senderMailId, senderPassword, requestString.toString().trim()).start();
						while( !server.isClosed() )
						{

							SystemMonitorUtil.updateLatestMemoryUtilization();
							SystemMonitorUtil.takeSystemScreenshot();

							out.println(SystemMonitorUtil.getResponceString());
							out.flush();

							System.out.println("sent response");
							Thread.sleep(6000);
						}
					}
					else
					{
						out.println("Faild");
						out.flush();
						out.close();
						socket.close();
					}

				}

			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

}
