package com.BITest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.Queue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Receive {
	static String jsonOutput; 
	private static final String QUEUE_NAME = "camelotQueue";
	static private Queue.DeclareOk dok;
	
	 
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		// set connection info
		
		factory.setHost("10.202.190.137");
		factory.setPort(32430);
                                        
		//factory.setHost("10.100.147.102");
		//factory.setPort(30405);
		factory.setUsername("lotos10");
		factory.setPassword("lotos10");
		// create the connection

		final Connection connection = factory.newConnection();

		// create the channel
		final Channel channel = connection.createChannel();
		
				
		System.out.println("Connection made successfully");
		/*Map<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("length", 1000);*/
		dok = channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		
		System.out.println("Message Count in Queue is:" + dok.getMessageCount());

		System.out.println(" [*] Consumer : waiting for messages.");
		// channel.basicQos(100, true);

		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonParser jp = new JsonParser();
				JsonElement je = jp.parse(message.toString());
				jsonOutput = gson.toJson(je);
				System.out.println(" [x] Received '" + jsonOutput + "'");
				long deliveryTag = envelope.getDeliveryTag();
				/*if (deliveryTag > dok.getMessageCount()){
					channel.abort(); 
					return;
				}*/
				System.out.println("Delivery Tag:" + deliveryTag);
				
				//Acknowledge the received messages
				//channel.basicAck(deliveryTag, true);
	
				// System.out.println("Positive acknowledged Delivery Tag:" + deliveryTag);
				// Requeuing the Messages
				//channel.basicNack(deliveryTag, false, true);
				//System.out.println("Negative acknowledged Delivery Tag:" + deliveryTag);
				System.out.println("All messages are placed back in Queue");
				
			}
			
			@Override
			public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
				//System.out.println("handleShutDownSignal called");
				try {
					connection.close();
					//System.out.println("Connection closed");
				} catch (IOException e) {
					// TODO Auto-generated catch block\
					e.printStackTrace();
				}	
			}
			
			
			@Override 
			public void handleConsumeOk(String consumerTag) {
				//System.out.println("handleConsumeOk called");
			}
		};

		boolean autoAck = true;
		// consuming message
		channel.basicConsume(QUEUE_NAME, autoAck, consumer);
		
	}
}