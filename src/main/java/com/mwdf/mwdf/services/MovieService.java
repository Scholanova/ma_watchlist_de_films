package com.mwdf.mwdf.services;

import com.mwdf.mwdf.entity.Movie;
import com.mwdf.mwdf.exception.MovieException;

public interface MovieService {


    //public Movie searchMovie(String params) throws MovieException;
	public String getMovie(int movieId);
    public String searchMovies(String params);
    public String getUrlContent(String lien);
    public String getLatestMovie();
    public String getRandomMovie();
    

}
