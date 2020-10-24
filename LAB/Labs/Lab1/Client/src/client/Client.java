/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author biar
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<String> benzAut = new LinkedList<String>();
        List<String> clients = getClients();
        System.out.println("Clients:" + clients);
        Iterator it = clients.iterator();
        while(it.hasNext()){
            String pair = (String) it.next();
            String pairDiv[] = pair.split(",");
            int id = Integer.parseInt(pairDiv[0]);
            List<String> op = getOperationsByClientID(id);
            System.out.println("client: " + id + ", operations: " + op);
            Iterator it2 = op.iterator();
            while(it2.hasNext()){
                String opI = (String) it2.next();
                int opID = Integer.parseInt(String.valueOf(opI.charAt(1)));
                String details = getOperationDetailsByID(opID);
                System.out.println("Details: " + details.replace("[", "").replace("]", ""));
                String detList[] = details.replace("[", "").replace("]", "").split(",");
                System.out.println(detList[4]);
                if(detList[4].equals("benzina autostrada")){
                    System.out.println("Client benzina:" + pairDiv[1]);
                }
            }
        }
    }

    private static List<String> getClients() {
        com.softeng.server.ServerMethodsService service = new com.softeng.server.ServerMethodsService();
        com.softeng.server.ServerMethodsInterface port = service.getServerMethodsPort();
        return port.getClients();
    }

    private static List<String> getOperationsByClientID(int arg0) {
        com.softeng.bankws.BankImplService service = new com.softeng.bankws.BankImplService();
        com.softeng.bankws.BankIFace port = service.getBankImplPort();
        return port.getOperationsByClientID(arg0);
    }

    private static String getOperationDetailsByID(int arg0) {
        com.softeng.bankws.BankImplService service = new com.softeng.bankws.BankImplService();
        com.softeng.bankws.BankIFace port = service.getBankImplPort();
        return port.getOperationDetailsByID(arg0);
    }
    
}
