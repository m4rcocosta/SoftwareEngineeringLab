/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.bankserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author biar
 */

@XmlRootElement(name = "Person")
public class Person {
    
    private String name;
    private String surname;
    private int id;
    private List<Operation> operations = new ArrayList<Operation>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.surname);
        hash = 89 * hash + this.id;
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
        final Person other = (Person) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        return true;
    }
    
    private Operation findById(int id){
        for(Operation op : operations){
            if(op.getId() == id){
                return op;
            }
        }
        return null;
    }
    
    @GET
    @Path("{operationId}")
    public Operation getOperation(@PathParam("{operationId}") int operationId){
        return findById(operationId);
    }
    
    @POST
    @Path("")
    public Response createOperation(Operation operation){
        for(Operation op : operations){
            if(op.getId() == operation.getId()){
                return Response.status(Response.Status.CONFLICT).build();
            }
        }
        operations.add(operation);
        return Response.ok(operation).build();
    }
    
    @DELETE
    @Path("{operationId}")
    public Response deleteOperation(@PathParam("{opreationId}") int operationId){
        Operation operation = findById(operationId);
        if(operation == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        operations.remove(operation);
        return Response.ok().build();
    }
    
}
