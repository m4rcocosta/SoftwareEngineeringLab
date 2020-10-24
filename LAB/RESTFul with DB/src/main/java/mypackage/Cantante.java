/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author biar
 */

@XmlRootElement(name = "Cantante")
public class Cantante {
    
    private int idCantante;
    private String nomeCantante;
    //private List<Canzone> canzoni;
    
    public Cantante(){
        
    }
    
    public Cantante(int idCantante, String nomeCantante){
        this.idCantante = idCantante;
        this.nomeCantante = nomeCantante;
        //canzoni = new ArrayList<Canzone>();
    }
    
    /*
    public Canzone findById(int idCanzone){
        for(Canzone canz : canzoni){
            if(canz.getIdCanzone() == idCanzone){
                return canz;
            }
        }
        return null;
    }
    */

    public int getIdCantante() {
        return idCantante;
    }

    public void setIdCantante(int idCantante) {
        this.idCantante = idCantante;
    }

    public String getNomeCantante() {
        return nomeCantante;
    }

    public void setNomeCantante(String nomeCantante) {
        this.nomeCantante = nomeCantante;
    }

    /*
    public List<Canzone> getCanzoni() {
        return canzoni;
    }

    
    public void setCanzoni(List<Canzone> canzoni) {
        this.canzoni = canzoni;
    }
    */

    @Override
    public String toString() {
        return "Cantante{" + "idCantante=" + idCantante + ", nomeCantante=" + nomeCantante +  '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Cantante other = (Cantante) obj;
        if (this.idCantante != other.idCantante) {
            return false;
        }
        if (!Objects.equals(this.nomeCantante, other.nomeCantante)) {
            return false;
        }
        
        return true;
    }
    
    
    
    
    
}
