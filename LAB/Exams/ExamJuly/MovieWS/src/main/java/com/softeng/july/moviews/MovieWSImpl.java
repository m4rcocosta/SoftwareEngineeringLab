/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.july.moviews;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author marco
 */

@WebService(endpointInterface="com.softeng.july.moviews.MovieWSInterface")
public class MovieWSImpl implements MovieWSInterface {
    private List<Movie> movies;
    private List<Director> directors;
    
    private static String url;

    private static Connection connection = null;
    private static Statement statement = null;
    
    public MovieWSImpl(String url) {
        this.url = url;
        movies = new ArrayList<Movie>();
        directors = new ArrayList<Director>();
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
    public Movie getMovie(int id) {
        for(Movie movie: movies) if(movie.getId() == id) return movie;
        return null;
    }

    @Override
    public Director getDirector(int id) {
        for(Director director: directors) if(director.getId() == id) return director;
        return null;
    }
    
    private void selectAllDirectors() {
        String query = "Select * from Directors";
        
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
                directors.add(new Director(result.getInt("id"), result.getString("name"), result.getString("yearOfBirth")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void selectAllMovies() {
        String query = "Select * from Movies";
        
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
                movies.add(new Movie(result.getInt("id"), result.getString("title"), result.getString("year"), getDirector(result.getInt("directorId"))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
