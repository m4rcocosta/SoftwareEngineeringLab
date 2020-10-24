/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author biar
 */
public class Database {
    
    private String url = "jdbc:sqlite:produzioni.db";
    private Connection conn = null;
	
    public Database(String url) {
	this.url = url;
    }
    
    public Database(){
        
    }
    
    public void connect() {
        try {
        // create a connection to the database
        conn = DriverManager.getConnection(url);
            
        System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    public void populateDb(){
        String sql = "INSERT INTO cantanti(idCantante, nomeCantante) VALUES(?,?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setString(2, "Mauro");
            ps.executeUpdate();
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 2);
            ps.setString(2, "Giulio");
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 3);
            ps.setString(2, "Valerio");
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql2 = "INSERT INTO canzoni(idCanzone, idCantante, nomeCanzone, anno) VALUES(?,?,?,?)";
        PreparedStatement ps2;
        try {
            ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, 1);
            ps2.setInt(2, 1);
            ps2.setString(3, "Ciao");
            ps2.setInt(4, 1234);
            ps2.executeUpdate();
            
            ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, 2);
            ps2.setInt(2, 1);
            ps2.setString(3, "Lulu");
            ps2.setInt(4, 1244);
            ps2.executeUpdate();
            
            ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, 3);
            ps2.setInt(2, 2);
            ps2.setString(3, "Vado via");
            ps2.setInt(4, 2033);
            ps2.executeUpdate();
            
            ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, 4);
            ps2.setInt(2, 3);
            ps2.setString(3, "Torno");
            ps2.setInt(4, 2003);
            ps2.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void disConnect() {
        try {
            conn.close();
	} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
    }
    
    
    public void createNewTable() {
        // SQLite connection string
        //String url = "jdbc:sqlite:products.db";
        PreparedStatement pstmt;
        String str = "DROP TABLE canzoni;";
        //PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(str);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String str2 = " DROP TABLE cantanti;";
        try {
            pstmt = conn.prepareStatement(str2);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS canzoni (\n"
                + "	idCanzone integer PRIMARY KEY,\n"
                + "     idCantante integer NOT NULL, \n"
                + "     nomeCanzone text NOT NULL, \n"
                + "	anno integer \n"
                + ");";
        
        String sql2 = "CREATE TABLE IF NOT EXISTS cantanti (\n"
                + "idCantante integer PRIMARY KEY, \n"
                + "nomeCantante text NOT NULL \n"
                + ");";
        
        //PreparedStatement pstmt = null;
        
        try {
            pstmt = conn.prepareStatement(sql);
            // create a new table
            pstmt.executeUpdate();
            System.out.println("New canzoni table created");
            pstmt = conn.prepareStatement(sql2);
            pstmt.executeUpdate();
            System.out.println("New cantanti table created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    public ArrayList<Cantante> selectAllCantanti() {
        String sql = "SELECT idCantante, nomeCantante FROM cantanti";
        ArrayList<Cantante> res = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
	            Cantante c1 = new Cantante();
                    c1.setIdCantante(rs.getInt("idCantante"));
	            c1.setNomeCantante(rs.getString("nomeCantante"));
	            res.add(c1);
	        }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public ArrayList<Canzone> selectAllCanzoni(){
        
        String sql = "SELECT idCanzone, idCantante, nomeCanzone, anno FROM canzoni";
        ArrayList<Canzone> res = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Canzone c1 = new Canzone();
                c1.setIdCanzone(rs.getInt("idCanzone"));
                c1.setIdCantante(rs.getInt("idCantante"));
                c1.setNomeCanzone(rs.getString("nomeCanzone"));
                c1.setAnno(rs.getInt("anno"));
                res.add(c1);
            }
        } catch (SQLException e){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
        }
        return res;
    }
    
    public Cantante selectCantante(int id){
        String sql = "SELECT idCantante, nomeCantante FROM cantanti WHERE idCantante = ?";
        PreparedStatement ps;
        Cantante c = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                c = new Cantante();
                c.setIdCantante(res.getInt("idCantante"));
                c.setNomeCantante(res.getString("nomeCantante"));
                //c = new Cantante(res.getInt("idCantante"), res.getString("nomeCantante"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return c;
    }
    
    public Canzone selectCanzone(int idCanzone, int idCantante){
        String sql = "SELECT idCanzone, idCantante, nomeCanzone, anno FROM canzoni WHERE idCanzone = ? AND idCantante = ?";
        PreparedStatement ps;
        Canzone c = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCanzone);
            ps.setInt(2, idCantante);
            ResultSet res = ps.executeQuery();
            while(res.next()){
                c = new Canzone();
                c.setIdCanzone(res.getInt("idCanzone"));
                c.setIdCantante(res.getInt("idCantante"));
                c.setNomeCanzone(res.getString("nomeCanzone"));
                c.setAnno(res.getInt("anno"));
                }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    /*
    public static void main(String[] argv){
        Database mydb = new Database();
        mydb.connect();
        mydb.createNewTable();
        mydb.disConnect();
    }
    */
    
}


