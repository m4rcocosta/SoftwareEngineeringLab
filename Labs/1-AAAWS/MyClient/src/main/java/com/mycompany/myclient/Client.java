/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myclient;

import java.text.ParseException;
import java.util.List;

/**
 *
 * @author biar
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    

    private static java.util.List<java.lang.String> getOperationDetailsByID(int i){
        com.maggio.banksoapserver.BankImplService service = new com.maggio.banksoapserver.BankImplService();
        com.maggio.banksoapserver.BankInterface port = service.getBankImplPort();
        return port.getOperationDetailsByID(i);
    }
    
    private static java.util.List<java.lang.String> getOperationsByClientID(int i) {
        com.maggio.banksoapserver.BankImplService service = new com.maggio.banksoapserver.BankImplService();
        com.maggio.banksoapserver.BankInterface port = service.getBankImplPort();
        return port.getOperationsByClientID(i);
    }
    
    
    private static java.util.List<java.lang.String> getClients(){
        com.mycompany.myaaawsserver.MyAAAWSImplService service = new com.mycompany.myaaawsserver.MyAAAWSImplService();
        com.mycompany.myaaawsserver.MyAAAWSInterface port = service.getMyAAAWSImplPort();
        return port.getClients();
    }



    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        
        System.out.println("Clients:");
        List<String> clients = getClients();
        for(String c : clients) {
            System.out.println(c);
        }
        
        System.out.println("------------");
        List<String> operations;
        int i;
        for(i = 1; i < 5; i++) {
            System.out.println("Operations of client " + i);
            operations = getOperationsByClientID(i);
            for(String s : operations) {
                List<String> details = getOperationDetailsByID(Integer.parseInt(s));
                System.out.println(details);
            }
        }
    }
    
}
