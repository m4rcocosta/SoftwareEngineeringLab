package com.softeng.moviesws;

import java.util.Objects;

public class Director {
    private int ID;
    private String name;
    private String yearOfBirth;

    public Director(int ID, String name, String yearOfBirth) {
        this.ID = ID;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.ID;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.yearOfBirth);
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
        final Director other = (Director) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.yearOfBirth, other.yearOfBirth)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Director " + ID + ": " + name + " (" + yearOfBirth + ")";
    }
}
