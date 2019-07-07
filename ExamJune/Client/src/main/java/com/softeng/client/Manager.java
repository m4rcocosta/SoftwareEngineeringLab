/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.client;

import java.util.Properties;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author biar
 */
public class Manager implements MessageListener {
    
    TopicConnection topicConnection;
    TopicSession topicSession = null;
    Destination destination = null;
    Context jndiContext = null;
    ConnectionFactory topicConnectionFactory = null;
    
    public Manager() {

        String destinationName = "dynamicTopics/Professors";

        try {

            Properties props = new Properties();
            props.setProperty(
                    Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            jndiContext = new InitialContext(props);

            topicConnectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
            destination = (Destination) jndiContext.lookup(destinationName);
            topicConnection = (TopicConnection) topicConnectionFactory.createConnection();
            topicSession = (TopicSession) topicConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            TopicSubscriber topicSubscriber = 
                    topicSession.createSubscriber((Topic) destination);
            topicSubscriber.setMessageListener(this);

        } catch (NamingException err) {
            err.printStackTrace();
        } catch (JMSException err) {
            err.printStackTrace();
        }
    }
    
    public void start(){
        try {
            topicConnection.start();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
    public void stop(){
        try {
            topicConnection.stop();
        } catch (JMSException ex) {
            ex.printStackTrace(); 
        }
    }
    
    private static Professor getProfDetails(int arg0) {
        com.softeng.serverwsdl.ServerMethodsService service = new com.softeng.serverwsdl.ServerMethodsService();
        com.softeng.serverwsdl.ServerMethodsInterface port = service.getServerMethodsPort();
        String name = port.getDetails(arg0).getName();
        String surname = port.getDetails(arg0).getSurname();
        return new Professor(name, surname);
    }

    @Override
    public void onMessage(Message msg) {
        try {
            String idS = msg.getStringProperty("id");
            float ranking = msg.getFloatProperty("ranking");
            System.out.println("Id: " + idS + ", ranking: " + ranking);
            int id = Integer.parseInt(idS);
            Professor p = getProfDetails(id);
            System.out.println("Ricevuto id: " + idS + " con ranking  " + ranking + " ... bravo " + p.getName() + " " + p.getSurname());
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
}
