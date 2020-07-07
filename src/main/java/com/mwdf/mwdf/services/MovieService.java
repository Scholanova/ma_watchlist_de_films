package com.mwdf.mwdf.services;

import com.mwdf.mwdf.entity.Movie;
import com.mwdf.mwdf.entity.Result;
import com.mwdf.mwdf.exception.MovieException;

public interface MovieService {


    //public Movie searchMovie(String params) throws MovieException;
	public Movie getMovie(int movieId);
    public Result searchMovies(String params);
    public String getUrlContent(String lien);
    public Movie getLatestMovie();
    public Movie getRandomMovie();
    

}
