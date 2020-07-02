package com.mwdf.mwdf.services;

import com.mwdf.mwdf.entity.Movie;
import com.mwdf.mwdf.exception.MovieException;

public interface MovieService {


    public Movie searchMovie(String params) throws MovieException;

}
