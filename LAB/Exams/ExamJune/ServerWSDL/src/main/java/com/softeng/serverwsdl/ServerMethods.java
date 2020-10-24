/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.serverwsdl;

import java.util.Map;
import java.util.TreeMap;
import javax.jws.WebService;

/**
 *
 * @author biar
 */

@WebService(endpointInterface = "com.softeng.serverwsdl.ServerMethodsInterface")
public class ServerMethods implements ServerMethodsInterface {

    private final Map<Integer, Professor> professorDB = new TreeMap<Integer, Professor>();
    
    public ServerMethods(){
        Professor p1 = new Professor("Massimo", "Mecella");
        Professor p2 = new Professor("Giuseppe", "De Giacomo");
        Professor p3 = new Professor("Roberto", "Baldoni");
        Professor p4 = new Professor("Riccardo", "Rosati");
        Professor p5 = new Professor("Giuseppe", "Santucci");
        Professor p6 = new Professor("Maurizio", "Lenzerini");

        professorDB.put(1, p1);
        professorDB.put(2, p2);
        professorDB.put(3, p3);
        professorDB.put(4, p4);
        professorDB.put(5, p5);
        professorDB.put(6, p6);
    }
    
    @Override
    public Professor getDetails(int id) {
        return professorDB.get(id);
    }
    
}
