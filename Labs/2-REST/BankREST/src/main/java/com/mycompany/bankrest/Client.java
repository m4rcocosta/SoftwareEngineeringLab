/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankrest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author biar
 */
@XmlRootElement(name = "Client")
public class Client {
    
    private int id;
    private String name;
    
    private List<Operation> operations = new ArrayList<>();
    
    private Operation findById(int id) {
        for (Operation operation : operations) {
            if (operation.getId() == id) {
                return operation;
            }
        }
        return null;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @GET
    @Path("{clientId}")
    public Operation getOperation(@PathParam("operationId") int operationId) {
        return findById(operationId);
    }

    @POST
    @Path("{clientId}")
    public Response createOperation(Operation operation) {
        for (Operation element : operations) {
            if (element.getId() == operation.getId()) {
                return Response.status(Response.Status.CONFLICT).build();
            }
        }
        operations.add(operation);
        return Response.ok(operation).build();
    }

    @DELETE
    @Path("{clientId}")
    public Response deleteOperation(@PathParam("clientId") int operationId) {
        Operation operation = findById(operationId);
        if (operation == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        operations.remove(operation);
        return Response.ok().build();
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        hash = 31 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
