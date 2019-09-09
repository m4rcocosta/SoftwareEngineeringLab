package com.softeng.client;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        List<com.softeng.moviesws.Director> directors = getAllDirectors();
        List<com.softeng.moviesws.Movie> movies = getAllMovies();

        System.out.println("==============DIRECTORS===============");
        for(com.softeng.moviesws.Director director: directors) System.out.println(director.getName());
        System.out.println("==============MOVIES==================");
        for(com.softeng.moviesws.Movie movie: movies) System.out.println(movie.getTitle());
        
        System.out.println("==============GET DIRECTOR============");
        System.out.println(getDirector(3));
        System.out.println("==============GET MOVIE===============");
        System.out.println(getMovie(2));
    }

    private static List<com.softeng.moviesws.Director> getAllDirectors() { // Call Web Service Operation
        com.softeng.moviesws.MoviesWSImplService service = new com.softeng.moviesws.MoviesWSImplService();
        com.softeng.moviesws.MoviesWSInterface port = service.getMoviesWSImplPort();
        return port.getAllDirectors();
    } 

    private static List<com.softeng.moviesws.Movie> getAllMovies() {
        com.softeng.moviesws.MoviesWSImplService service = new com.softeng.moviesws.MoviesWSImplService();
        com.softeng.moviesws.MoviesWSInterface port = service.getMoviesWSImplPort();
        return port.getAllMovies();
    }

    private static String getDirector(int arg0) {
        com.softeng.moviesws.MoviesWSImplService service = new com.softeng.moviesws.MoviesWSImplService();
        com.softeng.moviesws.MoviesWSInterface port = service.getMoviesWSImplPort();
        return port.getDirector(arg0);
    }

    private static String getMovie(int arg0) {
        com.softeng.moviesws.MoviesWSImplService service = new com.softeng.moviesws.MoviesWSImplService();
        com.softeng.moviesws.MoviesWSInterface port = service.getMoviesWSImplPort();
        return port.getMovie(arg0);
    }
}
