/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientrest;

import java.io.InputStreamReader;
import java.net.URL;
import org.apache.http.client.methods.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import static javafx.scene.input.KeyCode.T;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.http.HttpResponse;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author biar
 */
public class ClientREST {

    
    /**
     * @param args the command line arguments
     */
   
   



    public static void main(String[] args) throws MalformedURLException, IOException, JAXBException {
        // TODO code application logic here
        
        
        //GET
        CloseableHttpClient client = HttpClients.createDefault();
        URL url = new URL("http://localhost:9001/clients/1");
        InputStream input = url.openStream();
        Client c0 = JAXB.unmarshal(new InputStreamReader(input), Client.class);
       

        
        int s1 = c0.getId();
        String s2 = c0.getName();
        List<Operation> ops = c0.getOperations();
        
        
        //System.out.println(s1);
        //System.out.println(s2);
        for (Operation o : ops) {
            System.out.println(o.getName());
        }
        
        
        //POST2
        Client c = new Client();
        c.setId(555);
        c.setName("Luca");
        HttpPost httpPost = new HttpPost("http://localhost:9001/clients/");
        //creazione dummy file
        JAXBContext jaxbContext = JAXBContext.newInstance(Client.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(c, new File("res.xml"));
        File file = new File("res.xml");
        InputStream targetStream = new FileInputStream(file);

        httpPost.setEntity(new InputStreamEntity(targetStream)); //set the object as i

        httpPost.setHeader("Content-Type", "text/xml");
        //httpPost.setHeader("Accept", "text/xml");
        HttpResponse response = client.execute(httpPost);
        System.out.print("\nPost con risorsa:\n"+c.getName()+"\neseguita con esito:\t"+response.getStatusLine() );
        
        //GET
        url = new URL("http://localhost:9001/clients/3");
        input = url.openStream();
        c0 = JAXB.unmarshal(new InputStreamReader(input), Client.class);
        
        s1 = c0.getId();
        s2 = c0.getName();
        ops = c0.getOperations();
        
        System.out.println();
        System.out.println("GET:");
        System.out.println(s1);
        System.out.println(s2);
        for (Operation o : ops) {
            System.out.println(o.getName());
        }
        
        //POST1
        Operation o = new Operation();
        o.setId(2);
        o.setName("chinotto");
        httpPost = new HttpPost("http://localhost:9001/clients/1/");
        //creazione dummy file
        jaxbContext = JAXBContext.newInstance(Operation.class);
        jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(o, new File("res.xml"));
        file = new File("res.xml");
        targetStream = new FileInputStream(file);

        httpPost.setEntity(new InputStreamEntity(targetStream)); //set the object as i

        httpPost.setHeader("Content-Type", "text/xml");
        //httpPost.setHeader("Accept", "text/xml");
        response = client.execute(httpPost);
        System.out.println("\nPost con risorsa:\n"+o.getName()+"\neseguita con esito:\t"+response.getStatusLine() );
        
        //DELETE
        HttpDelete httpDelete = new HttpDelete("http://localhost:9001/clients/1");
        response = client.execute(httpDelete);
        System.out.println("Delete on http://localhost:9001/clients/1/" + "\neseguita con esito:\t"+response.getStatusLine());
    }
    
}
