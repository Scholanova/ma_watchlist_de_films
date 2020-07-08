package com.mwdf.mwdf.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GenreResult {
    List<Genre> genres;

    public GenreResult() {
    }
    public GenreResult(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "GenreResult{" +
                "genres=" + genres +
                '}';
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
