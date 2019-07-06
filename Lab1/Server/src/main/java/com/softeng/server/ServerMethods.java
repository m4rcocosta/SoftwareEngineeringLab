/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.server;

import java.util.LinkedList;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author biar
 */

@WebService(endpointInterface = "com.softeng.server.ServerMethodsInterface")
public class ServerMethods implements ServerMethodsInterface{
    
    private List<String> clients = new LinkedList<String>();
    
    public ServerMethods(){
        String cl1 = "1,Massimo Mecella";
        clients.add(cl1);
        String cl2 = "2,Manuel Cerriani";
        clients.add(cl2);
    }

    @Override
    public String[] getClients() {
        return (String[]) clients.toArray(new String[0]);
    }
    
    
    
}
