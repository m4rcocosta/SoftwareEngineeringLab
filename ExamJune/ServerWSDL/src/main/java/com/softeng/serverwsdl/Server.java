/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.serverwsdl;

import javax.xml.ws.Endpoint;

/**
 *
 * @author biar
 */
public class Server {
    public static void main(String args[]) throws InterruptedException{
        ServerMethods implementor = new ServerMethods();
        String address = "http://localhost:8080/ProfessorManagement";
        Endpoint.publish(address, implementor);
        while(true){}
    }
}
