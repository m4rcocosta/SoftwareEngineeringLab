/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.professorws;

import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;

@WebService(endpointInterface="com.softeng.professorws.ProfessorWSInterface")
public class ProfessorWSImpl implements ProfessorWSInterface{
    private final Map<String,Professor> professorData;

    public ProfessorWSImpl() {
        this.professorData = new HashMap<String,Professor>();
    }
    
    void insertData(String key, Professor professor) {
        this.professorData.put(key, professor);
    }

    @Override
    public Professor getDetails(String id) {
        System.out.println("Info request with ID = "+id);
        return this.professorData.get(id);
    }
    
}
