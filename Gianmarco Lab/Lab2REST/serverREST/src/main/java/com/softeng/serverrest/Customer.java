package com.softeng.serverrest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
    private int ID;
    private String name;
    private String surname;
    private final List<Operation> operations;

    public Customer(int ID, String name, String surname) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.operations = new ArrayList<Operation>();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.ID;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.surname);
        hash = 67 * hash + Objects.hashCode(this.operations);
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
        final Customer other = (Customer) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.operations, other.operations)) {
            return false;
        }
        return true;
    }
    
    boolean addOperationToCustomer(Operation op) {
        return this.getOperations().add(op);
    }
    
    void addOperation(Operation op) {
        this.operations.add(op);
    }
    
}
