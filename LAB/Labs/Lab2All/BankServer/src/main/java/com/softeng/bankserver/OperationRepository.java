/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.bankserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author biar
 */

@Path("/Bank")
public class OperationRepository {
    
    private Map<Integer, Person> people = new HashMap<Integer, Person>();
    private List<Operation> operations = new ArrayList<Operation>();
    
    {
        Operation op1 = new Operation(0, "[2019-03-22,130,cena al ristorante]");
        Operation op2 = new Operation(1, "[2019-03-19,30,benzina autostrada]");
        Operation op3 = new Operation(2, "[2019-03-18,1400,riparazione motorino]");
        Operation op4 = new Operation(3, "[2019-03-17,600,festa]");
        Operation op5 = new Operation(4, "[2019-03-17,45,benzina autostrada]");

        
        operations.add(op1);
        operations.add(op2);
        operations.add(op3);
        operations.add(op4);
        operations.add(op5);

        List<Operation> operations1 = new ArrayList<Operation>();
        operations1.add(op1);
        operations1.add(op2);

        List<Operation> operations2 = new ArrayList<Operation>();
        operations2.add(op3);
        operations2.add(op4);

        List<Operation> operations3 = new ArrayList<Operation>();
        operations3.add(op5);

        Person p1 = new Person();
        p1.setId(0);
        p1.setName("Massimo");
        p1.setSurname("Mecella");
        p1.setOperations(operations1);

        Person p2 = new Person();
        p2.setId(1);
        p2.setName("Miguel");
        p2.setSurname("Ceriani");
        p2.setOperations(operations2);

        Person p3 = new Person();
        p3.setId(2);
        p3.setName("Francesco");
        p3.setSurname("Leotta");
        p3.setOperations(operations3);

        people.put(0, p1);
        people.put(1, p2);
        people.put(2, p3);

    }
    
    private List<Operation> getOperationsByPersonId(int personId){
        for(Map.Entry<Integer, Person> person : people.entrySet()){
            if(person.getKey() == personId){
                return (ArrayList<Operation>) person.getValue().getOperations();
            }
        }
        return null;
    }
    
    private Operation getOperationDetailsById(int opId){
        return operations.get(opId);
    }
    
    @GET
    @Path("/people/{personId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
    public List<Operation> getOperationsByUserId(@PathParam("personId") int personId){
        return getOperationsByPersonId(personId);
    }
    
    @GET
    @Path("/operations/{opId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
    public Operation getOpById(@PathParam("opId") int opId){
        return getOperationDetailsById(opId);
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateOperation(Operation op){
        //Operation oper = new Operation(op.getId(), op.getDescription());
        System.out.println("Desc: " + op.getDescription());
        addOperation(op);
        return Response.status(Response.Status.CREATED).build();
    }
    
    private Operation findById(int id) {
        for (Operation op : operations) {
            if (op.getId() == id) {
                return op;
            }
        }
        return null;
    }
    
    private void addOperation(Operation operation) {
        for(Operation op : operations) {
            if(op.getId() == operation.getId()){
                throw new OperationAlreadyExists();
            }
        }
        operations.add(operation);
    }
    
}
