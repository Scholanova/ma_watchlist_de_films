package com.mwdf.mwdf.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mwdf.mwdf.entity.Movie;
import com.mwdf.mwdf.entity.Result;
import com.mwdf.mwdf.exception.MovieException;
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
<<<<<<< mwdf-Issue-2A
    private final String LANG = "fr-FR";
=======


    /*
    public Movie searchMovie(@PathVariable("params") String params) throws MovieException {
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+TOKEN+"&query="+params;
        String s = getUrlContent(url);
        Movie m = new Movie();
        try {
            ObjectMapper mapper = new ObjectMapper();
            m = mapper.readValue(s, Movie.class);
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return m;

    }
    */
    public String searchMovies(String params) {
    	String url = "https://api.themoviedb.org/3/search/movie?api_key="+TOKEN+"&query="+params;
        return getUrlContent(url);
    }
    
    public String search(@PathVariable("params") String params) {
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+TOKEN+"&query="+params;
        return getUrlContent(url);
    }
>>>>>>> update entity movie and added result entity

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
<<<<<<< mwdf-Issue-2A
    public String getMovie(int movieId) {
    	String url = "https://api.themoviedb.org/3/movie/"+ movieId+"?api_key="+TOKEN+"&language="+LANG;	
    	return getUrlContent(url);
    }
    /*
    public Movie searchMovie(@PathVariable("params") String params) throws MovieException {
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+TOKEN+"&query="+params;
        String s = getUrlContent(url);
        Movie m = new Movie();
        try {
            ObjectMapper mapper = new ObjectMapper();
            m = mapper.readValue(s, Movie.class);
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return m;

    }
    */
    public String searchMovies(String params) {
    	String url = "https://api.themoviedb.org/3/search/movie?api_key="+TOKEN+"&query="+params;
        return getUrlContent(url);
    }
    public String search(@PathVariable("params") String params) {
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+TOKEN+"&query="+params;
        return getUrlContent(url);
    }
	public String getLatestMovie() {
		String url = "https://api.themoviedb.org/3/movie/latest?api_key="+TOKEN+"&language="+LANG;
		return getUrlContent(url);
	}
	public String getRandomMovie() {
		String jsonLatestMovie = getLatestMovie();
		Movie latest = parseJsonToMovie(jsonLatestMovie);
		int idLatestMovie = latest.getId();
		int randomMovieId = (int) (Math.random() * ( idLatestMovie - 100 ));
		return getMovie(randomMovieId);
	}
	private Movie parseJsonToMovie(String json) {		
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
=======
>>>>>>> update entity movie and added result entity
}
