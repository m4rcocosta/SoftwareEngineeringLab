/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.bankclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author biar
 */
public class RestClient {
    
    private static final String REST_URI = "http://localhost:8080/Bank/people";
    private static Client client = ClientBuilder.newClient();
    
    public static void main(String args[]){
        List<Operation> list = getXmlPerson(0);
        System.out.println("Operations");
        for(Operation op : list){
            System.out.println(op.getDescription());
        }
    }
    
    private static List<Operation> getXmlPerson(int id){
        Operation ops[] = client.target(REST_URI).path(String.valueOf(id)).request(MediaType.APPLICATION_XML).get(Operation[].class);
        return Arrays.stream(ops).collect(Collectors.toList());
    }
    
}
