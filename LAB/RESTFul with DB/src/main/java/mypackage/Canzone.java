/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author biar
 */

@XmlRootElement(name = "Canzone")
public class Canzone {
    
    private int idCanzone;
    private int idCantante;
    private String nomeCanzone;
    private int anno;
    
    public Canzone(){
        
    }
    
    public Canzone(int idCanzone, int idCantante, String nomeCanzone, int anno){
        this.anno = anno;
        this.idCantante = idCantante;
        this.idCanzone = idCanzone;
        this.nomeCanzone = nomeCanzone;
    }

    public int getIdCanzone() {
        return idCanzone;
    }

    public void setIdCanzone(int idCanzone) {
        this.idCanzone = idCanzone;
    }

    public int getIdCantante() {
        return idCantante;
    }

    public void setIdCantante(int idCantante) {
        this.idCantante = idCantante;
    }

    public String getNomeCanzone() {
        return nomeCanzone;
    }

    public void setNomeCanzone(String nomeCanzone) {
        this.nomeCanzone = nomeCanzone;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    @Override
    public String toString() {
        return "Canzone{" + "idCanzone=" + idCanzone + ", idCantante=" + idCantante + ", nomeCanzone=" + nomeCanzone + ", anno=" + anno + '}';
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
        final Canzone other = (Canzone) obj;
        if (this.idCanzone != other.idCanzone) {
            return false;
        }
        if (this.idCantante != other.idCantante) {
            return false;
        }
        if (this.anno != other.anno) {
            return false;
        }
        if (!Objects.equals(this.nomeCanzone, other.nomeCanzone)) {
            return false;
        }
        return true;
    }
    
    
    
}
