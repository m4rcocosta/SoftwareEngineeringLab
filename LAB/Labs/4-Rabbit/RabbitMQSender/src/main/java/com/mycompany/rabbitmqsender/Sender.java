/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmqsender;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**
 *
 * @author biar
 */
public class Sender {

    private final static String QUEUE_NAME = "hello";
    public static void main(String[] argv) throws Exception {
        
        //Create a connection with the server
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost("localhost"); //Localhost or ip address
        try (Connection conn = cf.newConnection();
            Channel channel = conn.createChannel()) {
            
            //Create a queue and send a message to that queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Dio Si!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        
        
            int i = 0;
            for(i = 0; i < 10; i++) {
                message = String.valueOf(i);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            }
        }
        
    }
}
