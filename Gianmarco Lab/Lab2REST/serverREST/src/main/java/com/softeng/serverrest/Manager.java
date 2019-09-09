package com.softeng.serverrest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Bank")
public class Manager {
    private final Map<Integer, Customer> customers;
    private final List<Operation> operations;
    
    public Manager() {
        this.customers = new HashMap<Integer, Customer>();
        this.operations = new ArrayList<Operation>();
        
        System.out.println("Initialize data...");
        
        
        Customer c1 = new Customer(0, "Massimo", "Mecella");
        Customer c2 = new Customer(1, "Miguel", "Ceriani");
        Customer c3 = new Customer(2, "Francesco", "Leotta");
        this.customers.put(0, c1);
        this.customers.put(1, c2);
        this.customers.put(2, c3);
        System.out.println("Customers added");
        
        
        Operation op1 = new Operation(0, "[2019-03-22,130,cena al ristorante]");
        Operation op2 = new Operation(1, "[2019-03-19,30,benzina autostrada]");
        Operation op3 = new Operation(2, "[2019-03-18,1400,riparazione motorino]");
        Operation op4 = new Operation(3, "[2019-03-17,600,festa]");
        Operation op5 = new Operation(4, "[2019-03-17,45,benzina autostrada]");
        this.operations.add(op1);
        this.operations.add(op2);
        this.operations.add(op3);
        this.operations.add(op4);
        this.operations.add(op5);
        System.out.println("Operations added");
        
        if (!(c1.addOperationToCustomer(op1) && c1.addOperationToCustomer(op2) && 
              c2.addOperationToCustomer(op3) && c2.addOperationToCustomer(op4) &&
              c3.addOperationToCustomer(op5)) ) { 
            
            System.out.println("Problems with appending");
        }
        
        
        System.out.println("Data entered successful");
    }
    
    private Customer getCustomerById(int id) {
        return customers.get(id);
    }
    
    private Operation getOperationById(int id) {
        Iterator<Operation> it = operations.iterator();
        while (it.hasNext()) {
            Operation res = it.next();
            if (res.getID() == id) {
                return res;
            }
        }
        
        return null;
    }
    
    private List<Operation> getOperationsByCustomerId(int id) {
        return this.getCustomerById(id).getOperations();
    }
    
    @GET
    @Path("/customers/{ID}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
    public Customer getCustomer(@PathParam("ID")int ID) {
        return this.getCustomerById(ID);
    }
    @GET
    @Path("/customers")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
    public String getAllCustomer() {
        return this.customers.toString();
    }
    
    @GET
    @Path("/operations/{ID}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
    public Operation getOperation(@PathParam("ID")int ID) {
        return this.getOperationById(ID);
    }
    
    @GET
    @Path("/customers/{ID}/operations")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} )
    public List<Operation> getOperationsByCustomer(@PathParam("ID")int ID) {
        return this.getOperationsByCustomerId(ID);
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addOperation(Operation op) {
        if (this.getOperationById(op.getID()) != null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        
        this.operations.add(op);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addCustomer(Customer c) {
        if (this.getCustomer(c.getID()) != null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        
        this.customers.put(c.getID(), c);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @POST
    @Path("/addOperationToCustomer/{ID}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addOperationToCustomer(@PathParam("ID")int ID, Operation op) {
        // Customer not exists
        if (this.getCustomer(ID) == null) {
            System.out.print("[ERROR] Customer not exists, try to add to operation list... ");
            // Operation already exists
            if (this.getOperationById(op.getID()) != null) {
                System.out.println("Operation already exists, not added");
            }
            // Operation not exists
            else {
                this.operations.add(op);
                System.out.println("Operation added");
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        // Customer exists
        else {
            Operation res = this.getOperationById(ID);
            // Operation already exists
            if (res != null) {
                System.out.println("[WARNING] Operation already added, add it to customer");
                this.getCustomerById(ID).addOperation(res);
                return Response.status(Response.Status.CONFLICT).build();
            }
            // Operation not exists
            else {
                this.operations.add(res);
                System.out.println("Operation added to main list");
                return Response.status(Response.Status.CREATED).build();
            }
        }
    }
    
    @DELETE
    @Path("/customers/{ID}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteCustomer(@PathParam("ID")int ID) {
        Customer res = this.getCustomerById(ID);
        if (res == null) {
            System.out.println("[ERROR] Customer not found");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        this.customers.remove(ID);
        return Response.status(Response.Status.OK).build();
    }
    
    
}
