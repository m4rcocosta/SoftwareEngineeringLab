package com.softeng.serverrest;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Operation {
    private int ID;
    private String info;

    public Operation(int ID, String info) {
        this.ID = ID;
        this.info = info;
    }

    public int getID() {
        return ID;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.ID;
        hash = 83 * hash + Objects.hashCode(this.info);
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
        final Operation other = (Operation) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.info, other.info)) {
            return false;
        }
        return true;
    }
    
}
