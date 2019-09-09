package com.softeng.clientrest;

import com.softeng.serverrest.Operation;
import com.softeng.serverrest.Customer;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Main {
    private static Client client = ClientBuilder.newClient();
    private static final String REST_URI_GET_CUSTOMER = "http://localhost:8080/Bank/customers";
    private static final String REST_URI_POST_OPERATION_TO_CUSTOMER = "http://localhost:8080/Bank/addOperationToCustomer";
    private static final String REST_URI_DELETE_CUSTOMER = "http://localhost:8080/Bank/customer";
    
    public static void main(String[] args) {
        System.out.println("Retrieve customer with id = 0");
        Customer res = getXmlCustomer(0);
        if (res == null) System.out.println("ERROR");
        else System.out.println("Customer: " + res.getName() + " " + res.getSurname());
        
        System.out.println("Post operation to customer with id = 3");
        Operation new_op = new Operation(10, "[2019-03-18,1400,riparazione motorino]");
        Response response = postOperationToCustomer(3, new_op);
        System.out.println("Response: " + response.toString());
        
        System.out.println("Delete Francesco Leotta (id=2)");
        response = deleteCustomer(2);
        System.out.println("Response: " + response.toString());
        
        System.out.println("Verify deleting");
        res = getXmlCustomer(2);
        if (res == null) System.out.println("OK");
        else System.out.println("ERROR");
        
    }
    
    private static Customer getXmlCustomer(int id) {
        Customer customer = client.target(REST_URI_GET_CUSTOMER).path(String.valueOf(id)).request(MediaType.APPLICATION_XML).get(Customer.class);
        return customer;
    }
    
    private static Response postOperationToCustomer(int id, Operation op) {
        return client.target(REST_URI_POST_OPERATION_TO_CUSTOMER).path(String.valueOf(id)).request(MediaType.APPLICATION_XML).post(Entity.entity(op, MediaType.APPLICATION_XML));
    }
    
    private static Response deleteCustomer(int id) {
        return client.target(REST_URI_DELETE_CUSTOMER).path(String.valueOf(id)).request(MediaType.APPLICATION_XML).delete();
    }
}