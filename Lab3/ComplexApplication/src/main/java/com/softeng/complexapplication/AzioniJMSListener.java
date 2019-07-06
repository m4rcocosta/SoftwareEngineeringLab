/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.complexapplication;

import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author biar
 */
public class AzioniJMSListener extends Observable implements MessageListener {
    
    private TopicConnection topicConnection;
    private TopicSession topicSession = null;
    private Destination destination = null;
    
    public AzioniJMSListener(Observer[] observers){
        for (Observer observer : observers){
            Context jndiContext = null;
            ConnectionFactory topicConnectionFactory = null;

            String destinationName = "dynamicTopics/Ordini";

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
                start();
            } catch (NamingException err) {
                err.printStackTrace();
            } catch (JMSException err) {
                err.printStackTrace();
            }
        }
    }
    
    public void start(){
        try {
            System.out.println("Ma qua ci vai?");
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

    @Override
    public void onMessage(Message msg) {
        try {
            String nome = msg.getStringProperty("Nome");
            float valore = msg.getFloatProperty("Valore");
            System.out.println("Name: " + nome + ", Valore: " + valore);
            Quotazione quotazione = new Quotazione(nome, valore);
            setChanged();
            super.notifyObservers(quotazione);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
