package com.softeng.serverjms;

import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
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


public class Producer {
    Properties properties = null;
    Context jndiContext = null;
    private TopicConnectionFactory connectionFactory = null;
    private TopicConnection connection = null;
    private TopicSession session = null;
    private Topic destination = null;
    private TopicPublisher topicPublisher;
    
    public Producer() {
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
            System.out.println("Producer initialized");
            
            connection.start();
            System.out.println("Producer started");
            
            while (true) {
                this.produce();
                
                try {
                    Thread.sleep(3000);
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
    
    public void produce() {
        try {
            TextMessage sendMsg = session.createTextMessage();
            
            String id = Integer.toString((int) (Math.random()*10 +1));
            float ranking = (float) Math.random()*100 +1;
            
            sendMsg.setStringProperty("id", id);
            sendMsg.setFloatProperty("ranking", ranking);
            
            topicPublisher.send(sendMsg);
            System.out.println("Message published");
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
}
