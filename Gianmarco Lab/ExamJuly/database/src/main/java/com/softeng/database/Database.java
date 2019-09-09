package com.softeng.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private final String url;
    private static Statement stmt = null;

    public Database(String url) {
        this.url = url;
        createNewDatabase(url);
    }
    
    public void createNewDatabase(String url) {
        
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void createNewTable(String nameTable, String[] attributes) {
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + nameTable + " (\n";
        for (int i = 0; i < attributes.length; i++) {
            sql += attributes[i];
        }
        sql += ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute("drop table if exists " + nameTable);
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Table " + nameTable + " created");
    }
    
    public void insert(String tableScheme, String[] inputData) {
        String sql = "INSERT INTO "+ tableScheme + " VALUES(";
        for (int i = 0; i < inputData.length; i++) {
            sql += "?";
            
            if (i == inputData.length-1) sql += ")";
            else sql += ",";
        }
 
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(inputData[0]));
            pstmt.setString(2, inputData[1]);
            pstmt.setString(3, inputData[2]);
            if (inputData.length == 4) pstmt.setInt(4, Integer.parseInt(inputData[3]));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void selectAll(){
        String sql = "SELECT ID, name, yearOfBirth FROM directors";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getString("yearOfBirth"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        sql = "SELECT ID, title, year, directorID FROM movies";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("ID") +  "\t" + 
                                   rs.getString("title") + "\t" +
                                   rs.getString("year") + "\t" +
                                   rs.getInt("directorID"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
