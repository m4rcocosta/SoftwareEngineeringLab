/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.serverjms;

import java.util.Properties;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author biar
 */
public class JMSManager {
    
    Properties properties = null;
    Context jndiContext = null;
    private TopicConnectionFactory connectionFactory = null;
    private TopicConnection connection = null;
    private TopicSession session = null;
    private Topic destination = null;
    private TopicPublisher topicPublisher;
    
    public JMSManager() {
        try {
            properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            jndiContext = new InitialContext(properties);
            connectionFactory = (TopicConnectionFactory) jndiContext.lookup("ConnectionFactory");
            destination = (Topic) jndiContext.lookup("dynamicTopics/Professors");
            
            connection = connectionFactory.createTopicConnection();
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            topicPublisher = session.createPublisher(destination);
            connection.start();
            while(true) {
                compra();
                try {
                    Thread.sleep(5000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (JMSException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
    }
    
    public void compra() {
        try {
            TextMessage sendMsg = session.createTextMessage();
            int id = (int) (Math.random() * 6 + 1);
            String idS = Integer.toString(id);
            float ranking = (float) Math.random() * 100 + 1;
            sendMsg.setStringProperty("id", idS);
            sendMsg.setFloatProperty("ranking", ranking);
            System.out.println("idS: " + idS + ", rank: " + ranking);
            topicPublisher.publish(sendMsg);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
}
