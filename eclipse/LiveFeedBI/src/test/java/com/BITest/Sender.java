package com.BITest;

import com.rabbitmq.client.*;

public class Sender {
	  
    private static final String QUEUE_NAME = "camelotQueue";
    ConnectionFactory factory = new ConnectionFactory();
     
    public void sendMessage(String text) throws Exception {
    	 factory.setHost("10.100.146.95");
    	    factory.setUsername("lotos10");
    	    factory.setPassword("lotos10");
    	    factory.setPort(30405);
    	    Connection connection = factory.newConnection();
    	    Channel channel = connection.createChannel();
    	    System.out.println("connection created successfuly");
  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        while(true)
        {
      /*  string message;
        channel.basicPublish("", QUEUE_NAME,  null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");*/
        }
    }
}
