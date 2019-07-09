/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softeng.july.moviews;

import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author marco
 */

@WebService
public interface MovieWSInterface {
    public List<Movie> getAllMovies();
    public List<Director> getAllDirectors();
    public Movie getMovie(int id);
    public Director getDirector(int id);
}
