package com.softeng.moviesws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jws.WebService;


@WebService(endpointInterface="com.softeng.moviesws.MoviesWSInterface")
public class MoviesWSImpl implements MoviesWSInterface {
    private String url;
    private List<Movie> movies;
    private List<Director> directors;

    public MoviesWSImpl(String url) {
        this.url = url;
        this.movies = new ArrayList<Movie>();
        this.directors = new ArrayList<Director>();
        
        selectAllDirectors();
        selectAllMovies();
                
    }

    @Override
    public List<Movie> getAllMovies() {
        return movies;
    }

    @Override
    public List<Director> getAllDirectors() {
        return directors;
    }

    @Override
    public String getMovie(int id) {
        Iterator<Movie> it = movies.iterator();
        while (it.hasNext()) {
            Movie temp = it.next();
            if (temp.getID() == id) {
                return temp.toString();
            }
        }
        
        return null; // Here if there is not movie with id=="id"
    }

    @Override
    public String getDirector(int id) {
        Iterator<Director> it = directors.iterator();
        while (it.hasNext()) {
            Director temp = it.next();
            if (temp.getID() == id) {
                return temp.toString();
            }
        }
        
        return null; // Here if there is not director with id=="id"
    }
    
    private Director getDirectorByID(int id) {
        Iterator<Director> it = directors.iterator();
        while (it.hasNext()) {
            Director temp = it.next();
            if (temp.getID() == id) {
                return temp;
            }
        }
        
        return null; // Here if there is not director with id=="id"
    }
    
    private void selectAllMovies() {
        String sql = "SELECT ID, title, year, directorID FROM movies";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                movies.add(new Movie(rs.getInt("ID"), rs.getString("title"), 
                        getDirectorByID(rs.getInt("directorID")), rs.getString("year")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void selectAllDirectors() {
        String sql = "SELECT ID, name, yearOfBirth FROM directors";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                directors.add(new Director(rs.getInt("ID"), rs.getString("name"), 
                        rs.getString("yearOfBirth")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
