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
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author biar
 */
public class CompraJMSManager {

    //CompraFrame frame;
    Properties properties = null;
    Context jndiContext = null;
    private TopicConnectionFactory connectionFactory = null;
    private TopicConnection connection = null;
    private TopicSession session = null;
    private Topic destination = null;
    //private TopicSubscriber sub;
    private TopicPublisher topicPublisher;
    
    public CompraJMSManager() {
        //this.frame = frame;
        try {
            properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            jndiContext = new InitialContext(properties);
            connectionFactory = (TopicConnectionFactory) jndiContext.lookup("ConnectionFactory");
            destination = (Topic) jndiContext.lookup("dynamicTopics/Ordini");
            
            connection = connectionFactory.createTopicConnection();
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            topicPublisher = session.createPublisher(destination);
            connection.start();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public boolean compra(String name, float price, int quantity) {
        String user = Utente.getInstance().getUtente();
        System.out.println("In compra");
        if(user == null){
            return false;
        }
        try {
            TextMessage sendMsg = session.createTextMessage();
            sendMsg.setStringProperty("Utente", user);
            sendMsg.setStringProperty("Nome", name);
            sendMsg.setFloatProperty("Prezzo", price);
            sendMsg.setIntProperty("Quantita", quantity);
            sendMsg.setFloatProperty("Valore", price * quantity);
            //String query =
            //        "Utente = '" + user + "'" +
            //        " AND " +
            //        "Nome = '" + name + "'";
            //sub = session.createSubscriber(destination, query, true);
            //sub.setMessageListener(this);
            
            topicPublisher.publish(sendMsg);
            connection.stop();
            return true;
            //JOptionPane.showMessageDialog(frame, "La transazione \u00e8 andata a buon fine.", "Effettua Acquisti", 0);
        } catch (JMSException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    /*
    @Override
    public void onMessage(Message msg) {
        String infoMsg = null;
        try {
            TextMessage recMsg = (TextMessage) msg;
            if(recMsg != null) {
                if(recMsg.getBooleanProperty("Status"))
                    infoMsg = "L'acquisto \\u00e8 andato a buon fine";
                else
                    infoMsg = "L'acquisto non \u00e8 andato a buon fine";
            }
            sub.close();
        } catch (NumberFormatException ex) {
            infoMsg = "Errore nel riempimento di alcuni campi";
        } catch (JMSException ex) {
            Logger.getLogger(CompraJMSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(infoMsg != null) {
            super.setChanged(); // Rende attivo il cambiamento di stato
            super.notifyObservers(infoMsg);
        }
    }  
    */
}
