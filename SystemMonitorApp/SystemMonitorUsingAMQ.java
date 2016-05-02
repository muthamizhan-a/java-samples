package com.rzt.systemMonitor.service;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import com.rzt.systemMonitor.util.SystemMonitorUtil;

public class SystemMonitorUsingAMQ extends Thread {

	private Connection connection;
	private MessageProducer producer;
	private MessageConsumer consumer;
	private Session session;
	private String senderMailId = "";
	private String senderPassword = "";

	public SystemMonitorUsingAMQ( String ip, String port, String consumer, String producer, String senderMailId,
			String senderPassword ) throws Exception
	{
		this.senderMailId = senderMailId;
		this.senderPassword = senderPassword;
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"nio://" + ip + ":" + port + "?jms.prefetchPolicy.queuePrefetch=1000");
		// Create a Connection
		this.connection = connectionFactory.createConnection();
		this.connection.start();
		initializeProducer(producer);
		initializeConsumer(consumer);

	}

	public void run()
	{
		try
		{
			String request = receiveFromQueue();
			if( SystemMonitorUtil.isValidMailId(request) )
			{
				//new MailSender(senderMailId,senderPassword, request).start();
				while( true )
				{
					produceToQueue(SystemMonitorUtil.getResponceString());
					System.out.println("Responce sent.");
					Thread.sleep(6000);
				}

			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

	}

	private void initializeProducer( String queueName ) throws JMSException
	{
		this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(queueName);
		this.producer = session.createProducer(destination);
		this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	}

	private void initializeConsumer( String queueName ) throws JMSException
	{
		Destination destination = this.session.createQueue(queueName);
		this.consumer = this.session.createConsumer(destination);

	}

	private String receiveFromQueue() throws JMSException
	{
		String request = "";
		System.out.println("AMQ consumer is waiting to receive:");
		while( true )
		{
			Message message = this.consumer.receive(1000);
			if( message instanceof TextMessage )
			{
				TextMessage textMessage = (TextMessage) message;
				request = textMessage.getText();
				if( request != "" )
				{
					System.out.println("Received request:" + request);
					break;
				}
			}

		}
		return request;
	}

	private void produceToQueue( String msg ) throws JMSException
	{

		// CReate JSON object..
		TextMessage message = this.session.createTextMessage(msg);
		producer.send(message);

	}

}
