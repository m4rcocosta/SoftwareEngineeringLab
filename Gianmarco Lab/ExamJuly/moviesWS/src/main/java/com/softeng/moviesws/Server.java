package com.softeng.moviesws;

import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:/home/gianmarco/Scrivania/ExamJuly/database.db";
        String address = "http://localhost:8080/MoviesInterface";

        MoviesWSImpl implementor = new MoviesWSImpl(url);
        Endpoint.publish(address, implementor);

        System.out.println("Server run...");
        while (true) {}
    }
}
