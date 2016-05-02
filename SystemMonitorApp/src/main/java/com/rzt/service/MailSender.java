package com.rzt.systemMonitor.service;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.razorthink.blazentcommon.exception.RztRuntimeException;

public class MailSender extends Thread {

	private Properties props;
	private Session session;
	private String recipientEmail;
	private String senderUserName;
	private String senderPassword;

	public MailSender( String senderUserName, String senderPassword, String recipientEmail )
	{
		this.recipientEmail = recipientEmail;
		this.senderUserName = senderUserName;
		this.senderPassword = senderPassword;
		props = System.getProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.debug", "true");
	}

	public void run()
	{
		while( true )
		{
			try
			{
				sendEmail();
				Thread.sleep(6000);
			}
			catch( InterruptedException e )
			{
				e.printStackTrace();
			}
		}
	}

	private void createSession( final String hostUserName, final String hostPassword )
	{
		Authenticator authenticator = new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(hostUserName, hostPassword);
			}
		};
		session = Session.getInstance(props, authenticator);

	}

	private void sendEmail()
	{
		try
		{
			createSession(this.senderUserName, this.senderPassword);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderUserName));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("system activity screenshot");
			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart = new MimeBodyPart();
			DataSource fds = new FileDataSource(
					"/home/muthamizhan/workspace_0.1/SystemMonitorApp/src/main/resources/screenshot.png");

			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image>");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			// Send message
			Transport.send(message);
			System.out.println("Sent Mail successfully....");

		}
		catch( MessagingException mex )
		{
			mex.printStackTrace();
			RztRuntimeException exception = new RztRuntimeException(mex);
			throw exception;

		}
		catch( Exception e )
		{
			e.printStackTrace();
			RztRuntimeException exception = new RztRuntimeException(e);
			throw exception;
		}
	}

}
