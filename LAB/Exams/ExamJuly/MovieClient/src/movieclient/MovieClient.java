/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieclient;

import java.util.List;

/**
 *
 * @author marco
 */
public class MovieClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Director> directors = getAllDirectors();
        List<Movie> movies = getAllMovies();
        
        System.out.println("==============DIRECTORS===============");
        for(Director director: directors) System.out.println("Director{" + "id=" + director.getId() + ", name=" + director.getName() + ", yearOfBirth=" + director.getYearOfBirth() + '}');
        System.out.println("\n==============MOVIES===============");
        for(Movie movie: movies) System.out.println("Movie{" + "id=" + movie.getId() + ", title=" + movie.getTitle() + ", year=" + movie.getYear() + ", director=" + movie.getDirector().getName() + '}');
    }

    private static java.util.List<movieclient.Director> getAllDirectors() {
        movieclient.MovieWSImplService service = new movieclient.MovieWSImplService();
        movieclient.MovieWSInterface port = service.getMovieWSImplPort();
        return port.getAllDirectors();
    }

    private static java.util.List<movieclient.Movie> getAllMovies() {
        movieclient.MovieWSImplService service = new movieclient.MovieWSImplService();
        movieclient.MovieWSInterface port = service.getMovieWSImplPort();
        return port.getAllMovies();
    }

    private static Director getDirector(int arg0) {
        movieclient.MovieWSImplService service = new movieclient.MovieWSImplService();
        movieclient.MovieWSInterface port = service.getMovieWSImplPort();
        return port.getDirector(arg0);
    }

    private static Movie getMovie(int arg0) {
        movieclient.MovieWSImplService service = new movieclient.MovieWSImplService();
        movieclient.MovieWSInterface port = service.getMovieWSImplPort();
        return port.getMovie(arg0);
    }
    
}
