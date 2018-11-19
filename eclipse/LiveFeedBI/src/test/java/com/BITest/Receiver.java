package com.BITest;

import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.Basic;

import java.io.IOException;


public class Receiver {

	public static void main(String[] args) throws Exception {
	    String queueName = "camelotQueue";
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("10.100.147.102");
	    factory.setUsername("lotos10");
	    factory.setPassword("lotos10");
	    factory.setPort(30405);
	    Connection connection = factory.newConnection();
	    
	    Channel channel = connection.createChannel();
	    System.out.println("connection created successfuly");
	    
	    

	   channel.queueDeclare(queueName, true, false, false, null);
	    System.out.println(" [*] Waiting for messages...");
	    
	    int prefetchCount = 1;
        channel.basicQos(prefetchCount);

	    QueueingConsumer consumer = new QueueingConsumer(channel);
	    channel.basicConsume(queueName, true, consumer);
	 
	   

	    while (true) {
	        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	        String message = new String(delivery.getBody());
	        System.out.println(" [x] Received '" + message + "'");
	        long deliveryTag = delivery.getEnvelope().getDeliveryTag();
	        channel.basicAck(deliveryTag, true);
		    System.out.println("Positive acknowledged Delivery Tag:" + deliveryTag);
		    channel.basicNack(deliveryTag , true, true);
		    System.out.println("Requeue Message:" + deliveryTag);
		  // channel.basicRecover(true);
	    }
	    
	   
	    }
}
	
	
	 