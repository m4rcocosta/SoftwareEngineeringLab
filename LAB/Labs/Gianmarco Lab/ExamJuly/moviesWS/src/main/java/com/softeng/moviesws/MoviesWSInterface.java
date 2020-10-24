package com.softeng.moviesws;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface MoviesWSInterface {
    public List<Movie> getAllMovies();
    public List<Director> getAllDirectors();
    
    public String getMovie(int id);
    public String getDirector(int id);
}
