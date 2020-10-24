/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.bankws;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 *
 * @author biar
 */

public class Server {
    public static void main(String args[]) throws InterruptedException{
        BankImpl implementor = new BankImpl();
        String address = "http://localhost:8081/BankInterface";
        Endpoint.publish(address, implementor);
        while(true){}
    }
    
}
