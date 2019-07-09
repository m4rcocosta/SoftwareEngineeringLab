/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.july.moviews;

import javax.xml.ws.Endpoint;

/**
 *
 * @author marco
 */
public class Server {
    public static void main(String[] args){
        String url = "jdbc:sqlite:/home/marco/Scrivania/movie.db";
        MovieWSImpl implementor = new MovieWSImpl(url);
        String address = "http://localhost:8080/MovieInterface";
        Endpoint.publish(address, implementor);
        System.out.println("Server started...");
        while(true){}
    }
}
