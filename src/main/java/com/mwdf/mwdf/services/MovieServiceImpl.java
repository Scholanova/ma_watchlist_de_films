package com.mwdf.mwdf.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mwdf.mwdf.entity.Movie;
import com.mwdf.mwdf.entity.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class MovieServiceImpl implements MovieService{

    @Value("${apidb.token}")
    private String TOKEN;
    private final String LANG = "fr-FR";

    public String getUrlContent(String lien) {
        StringBuilder sb = new StringBuilder();
        try {


            URL url = new URL(lien);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                sb.append(inputLine);
            }
            in.close();
            //return "Hello World!!! API";
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return sb.toString();
    }
    public Movie getMovie(int movieId) {
    	String url = "https://api.themoviedb.org/3/movie/"+ movieId+"?api_key="+TOKEN+"&language="+LANG;
    	String json = getUrlContent(url);
    	return parseJsonToMovie(json);
    }

    public Result searchMovies(String params) {
    	String url = "https://api.themoviedb.org/3/search/movie?api_key="+TOKEN+"&query="+params;
        String json = getUrlContent(url);
        Result res = new Result();

        try {
            ObjectMapper mapper = new ObjectMapper();
            res = mapper.readValue(json, Result.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        for(Movie movie : res.getResults()){
            movie.initPosterFilm();
            movie.initBackDropFilm();
        }
        initMoviePosition(res);
        return res;
    }
    public String search(@PathVariable("params") String params) {
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+TOKEN+"&query="+params;
        return getUrlContent(url);
    }
	public Movie getLatestMovie() {
		String url = "https://api.themoviedb.org/3/movie/latest?api_key="+TOKEN+"&language="+LANG;
        String json = getUrlContent(url);
        return parseJsonToMovie(json);
	}
	public Movie getRandomMovie() {
		Movie latest = getLatestMovie();
		int idLatestMovie = latest.getId();
		int randomMovieId = (int) (Math.random() * ( idLatestMovie - 100 ));
		return getMovie(randomMovieId);
	}
	public Movie parseJsonToMovie(String json) {
		Movie m = new Movie();
		try {
			ObjectMapper mapper = new ObjectMapper();
			m = mapper.readValue(json, Movie.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

    private void initMoviePosition(Result res){
        int i = 1;
        for (Movie m : res.getResults()) {
            m.setPosition(i);
            i++;
        }
    }
}
