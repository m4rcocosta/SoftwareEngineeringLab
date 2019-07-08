/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.bankclient;

import com.softeng.bankserver.Operation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author biar
 */
public class RestClient {
    
    private static final String REST_URI_GET = "http://localhost:8080/Bank/people";
    private static final String REST_URI_PUT = "http://localhost:8080/Bank";
    
    private static Client client = ClientBuilder.newClient();
    
    public static void main(String args[]){
        List<Operation> list = getXmlPerson(0);
        System.out.println("Operations");
        Operation oper = new Operation(5, "[2019-03-18,1400,riparazione motorino]");
        for(Operation op : list){
            System.out.println(op.getDescription());
        }
        Response resp = postOperation(oper);
        System.out.println("Response post: " + resp);
    }
    
    private static List<Operation> getXmlPerson(int id){
        Operation ops[] = client.target(REST_URI_GET).path(String.valueOf(id)).request(MediaType.APPLICATION_XML).get(Operation[].class);
        return Arrays.stream(ops).collect(Collectors.toList());
    }
    
    private static Response postOperation(Operation op) {
        return client.target(REST_URI_PUT).request(MediaType.APPLICATION_XML).post(Entity.entity(op, MediaType.APPLICATION_XML));
    }
    
}
