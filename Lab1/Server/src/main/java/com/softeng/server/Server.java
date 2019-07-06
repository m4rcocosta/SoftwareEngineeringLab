/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.server;

import javax.xml.ws.Endpoint;

/**
 *
 * @author biar
 */
public class Server {
    
    public static void main(String args[]) throws InterruptedException{
    
        ServerMethods smts = new ServerMethods();

        String address = "http://localhost:8080/serverMethodsInterface";
        Endpoint.publish(address, smts);
        
        while(true){}
    
    }
    
}
