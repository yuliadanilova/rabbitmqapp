package com.rabbitmq.sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {

    private final static String EXCHANGE_NAME = "log";
    private final static String ROUTING_NAME = "routing";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            String message;
            int i = 0;
            while (i < 100) {
                message = String.valueOf(i);
                channel.basicPublish(EXCHANGE_NAME, ROUTING_NAME, null, message.getBytes());
                System.out.println(" Sent '" + message + "'");
                i++;
                Thread.sleep(2000);
            }
        }
    }
}

