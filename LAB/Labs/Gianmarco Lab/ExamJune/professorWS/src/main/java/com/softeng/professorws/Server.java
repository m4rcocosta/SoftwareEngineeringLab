package com.softeng.professorws;

import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args) {
        
        ProfessorWSImpl implementor = new ProfessorWSImpl();
        
        System.out.print("Populating with professor data...");
        implementor.insertData("0", new Professor("Massimo", "Mecella"));
        implementor.insertData("1", new Professor("Miguel", "Ceriani"));
        implementor.insertData("2", new Professor("Luca", "Becchetti"));
        implementor.insertData("3", new Professor("Andrea", "Vitaletti"));
        implementor.insertData("4", new Professor("Luca", "Iocchi"));
        implementor.insertData("5", new Professor("Giuseppe", "De Giacomo"));
        implementor.insertData("6", new Professor("Riccardo", "Rosati"));
        implementor.insertData("7", new Professor("Roberto", "Baldoni"));
        implementor.insertData("8", new Professor("Maurizio", "Lenzerini"));
        implementor.insertData("9", new Professor("Fabrizio", "D'Amore"));
        System.out.println(" data entered successfully");
        
        String address = "http://localhost:8080/ProfessorInterface";
        Endpoint.publish(address, implementor);
        
        System.out.println("Server run...");
        while (true) {}
    }
}
