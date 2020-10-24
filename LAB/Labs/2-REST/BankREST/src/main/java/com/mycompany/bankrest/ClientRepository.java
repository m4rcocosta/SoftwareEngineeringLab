/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankrest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author biar
 */

@Path("clients")
@Produces("text/xml")
public class ClientRepository {
    private Map<Integer, Client> clients = new HashMap<>();
    int i = 3;
 
    {
        Client c1 = new Client();
        Client c2 = new Client();
        c1.setId(1);
        c1.setName("C1");
        c2.setId(2);
        c2.setName("C2");
        
        Operation o1 = new Operation();
        Operation o2 = new Operation();
                
        o1.setId(1);
        o1.setName("Auto");
        o2.setId(2);
        o2.setName("Spesa");

        List<Operation> c1ops = new ArrayList<>();
        c1ops.add(o1);
        c1ops.add(o2);


        c1.setOperations(c1ops);

        clients.put(1, c1);
        clients.put(2, c2);
    }
    
    @GET
    @Path("")
    public Set<Integer> getClients() {
        return clients.keySet();
    }
    
    @GET
    @Path("{clientId}")
    public Client getClient(@PathParam("clientId") int clientId) {
        return findById(clientId);
    }

    
    @POST
    @Path("")
    public Response createClient(Client client) {
        clients.put(i,client);
        i++;
        return Response.ok(client).build();
    }
    
    @DELETE
    @Path("{clientId}")
    public Response deleteClient(@PathParam("clientId") int clientId) {
        System.out.println(clients);
        System.out.println("elimino il " + clientId);
        clients.remove(clientId);
        System.out.println(clients);
        return Response.ok().build();
    }
    

    @PUT
    @Path("{courseId}")
    public Response updateClient(@PathParam("clientId") int clientId, Client client) {
        Client existingClient = findById(clientId);
        if (existingClient == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (existingClient.equals(client)) {
            return Response.notModified().build();
        }
        clients.put(clientId, client);
        return Response.ok().build();
    }

    @Path("{clientId}/operations")
    public Client pathToOpearions(@PathParam("clientId") int clientId) {
        return findById(clientId);
    }
 
    private Client findById(int id) {
        for (Map.Entry<Integer, Client> client : clients.entrySet()) {
            if (client.getKey() == id) {
                return client.getValue();
            }
        }
        return null;
    }
}    
