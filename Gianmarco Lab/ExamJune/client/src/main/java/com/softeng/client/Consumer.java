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
import com.softeng.professorws.Professor;

public class Consumer implements MessageListener {
    
    TopicConnection topicConnection;
    TopicSession topicSession = null;
    Destination destination = null;
    Context jndiContext = null;
    ConnectionFactory topicConnectionFactory = null;
    
    public Consumer() {
        String destinationName = "dynamicTopics/Professors";
        
        try {
            Properties properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            jndiContext = new InitialContext(properties);
            
            topicConnectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
            destination = (Destination) jndiContext.lookup(destinationName);
            topicConnection = (TopicConnection) topicConnectionFactory.createConnection();
            topicSession = (TopicSession) topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            
            TopicSubscriber topicSubscriber = topicSession.createSubscriber((Topic) destination);
            topicSubscriber.setMessageListener(this);
            
            System.out.println("Consumer initialized");        
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
    public void start(){
        try {
            topicConnection.start();
            System.out.println("Consumer started");
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
            String id = msg.getStringProperty("id");
            float ranking = msg.getFloatProperty("ranking");
            
            com.softeng.client.Professor professor = getDetails(id);
            
            System.out.println("Ricevuto id : "+ id + " con ranking " + ranking 
                    + " ... bravo " + professor.getName() + " " + professor.getSurname());
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
    private static com.softeng.client.Professor getDetails(String arg0) { // Call Web Service Operation
        com.softeng.professorws.ProfessorWSImplService service = new com.softeng.professorws.ProfessorWSImplService();
        com.softeng.professorws.ProfessorWSInterface port = service.getProfessorWSImplPort();
        com.softeng.professorws.Professor result = port.getDetails(arg0);
        return new com.softeng.client.Professor(result.getName(), result.getSurname());
    }


}
