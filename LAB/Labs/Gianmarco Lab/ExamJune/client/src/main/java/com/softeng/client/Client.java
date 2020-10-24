package com.softeng.client;

public class Client {
    public static void main(String[] args) {
        String urlWS = "http://localhost:8080/ProfessorInterface";
        
        Consumer consumer = new Consumer();
        consumer.start();
    }
}
