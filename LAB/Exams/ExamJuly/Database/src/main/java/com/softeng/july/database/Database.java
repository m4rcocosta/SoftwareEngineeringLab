/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.july.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marco
 */

public class Database {
    private static String url;

    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preparedstatement = null;
    
    public Database(String url) {
        this.url = url;
        createDatabase();
    }
    
    private static void createDatabase() {
        System.out.println("Creating new database...");
        try {
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                System.out.println("Database created successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void createTable(String table, String[] attributes) {
        System.out.println("Creating table " + table);
        String query = "Create table " + table + " (";
        for(int i = 0; i < attributes.length - 1; i++) query += attributes[i] + ",";
        query += attributes[attributes.length - 1] + ");";
        
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            // create a new table
            statement.execute("drop table if exists " + table);
            statement.execute(query);
            System.out.println("Table " + table + " created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insert(String table, String[] values) {
        String query = "Insert into " + table + " values";
        String value = "(";
        for(int i = 0; i < values.length - 1; i++) value += values[i] + ",";
        value += values[values.length - 1] + ")";
        query += value;
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Tuple " + value + " inserted successfully in " + table + " table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
