package com.mwdf.mwdf.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.mwdf.mwdf.entity.Genre;
import com.mwdf.mwdf.entity.GenreResult;
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
import java.util.ArrayList;
import java.util.List;

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
                //System.out.println(inputLine);
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
        //System.out.println("parma " + params);
        if(params.isEmpty()){
            //return new Result();
            return null;
        }
        else{
            params = params.replaceAll(" ","%20");
        }
    	String url = "https://api.themoviedb.org/3/search/movie?api_key="+TOKEN+"&query="+params+"&language=+"+LANG;
        String json = getUrlContent(url);
        Result res = resultMapper(json);
        /*
        try {
            ObjectMapper mapper = new ObjectMapper();
            res = mapper.readValue(json, Result.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        */
        List<Movie> updatedMovie = new ArrayList<Movie>();
        /*
        for(Movie movie : res.getResults()){
            movie = getMovie(movie.getId());
            movie.initPosterFilm();
            movie.initBackDropFilm();

            movie.initGenreIfNull();
            movie.initBelongs_To_CollentionIfNull();
            movie.initSpoken_languagesIfNull();
            updatedMovie.add(movie);
        }
        */

        Movie tmp = new Movie();
        for(Movie movie : res.getResults()){
            tmp = getMovie(movie.getId());
            /*movie.initPosterFilm();
            movie.initBackDropFilm();
            */
            movie.initGenreIfNull();
            movie.initBelongs_To_CollentionIfNull();
            movie.initSpoken_languagesIfNull();
            if(tmp.getTitle()!= null || !tmp.getTitle().isEmpty()){
                movie.setTitle(tmp.getTitle());
            }

            if(!tmp.getOverview().isEmpty()){
                //System.out.println("is Etmpty ? :" + tmp.getOverview().isEmpty());
                //System.out.println("Resumé :" + tmp.getOverview());
                movie.setOverview(tmp.getOverview());
            }

            movie.setRuntime(tmp.getRuntime());
        }

        //res.setResults(updatedMovie);
        initMoviePosition(res);
        /*
        System.out.println("UPDATED MOVIES");
        for(Movie movie : res.getResults()){
            System.out.println(movie);
        }
        */

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
	public Result resultMapper(String json){
        Result res = new Result();

        try {
            ObjectMapper mapper = new ObjectMapper();
            res = mapper.readValue(json, Result.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void initMoviePosition(Result res){
        int i = 1;
        for (Movie m : res.getResults()) {
            m.setPosition(i);
            i++;
        }
    }

    public GenreResult getAllGenre(){
        List<Genre> list = new ArrayList<Genre>();
        String url = "https://api.themoviedb.org/3/genre/movie/list?api_key="+TOKEN+"&language="+LANG;
        String json = getUrlContent(url);
        GenreResult result = new GenreResult();
        try {
            ObjectMapper mapper = new ObjectMapper();
            /*CollectionType javaType = mapper.getTypeFactory()
                    .constructCollectionType(List.class, Genre.class);
            list = mapper.readValue(json,  javaType);
            */
            result = mapper.readValue(json, GenreResult.class);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public Movie getRandomMovieGenre(int idGenre){
        int page = (int) (Math.random() * ( 500 - 1 ));
        int randomId = (int) (Math.random() * ( 19 ));
        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+TOKEN+"&with_genres="+idGenre+"&page="+page+"&language="+LANG;
        String json = getUrlContent(url);
        Result result = resultMapper(json);
        return result.getResults().get(randomId);
    }

    @Override
    public Movie getPopularMovie() {

        int page = (int) (Math.random() * ( 500 - 1 ));
        int randomId = (int) (Math.random() * ( 19 ));
        String url = "https://api.themoviedb.org/3/movie/popular?api_key="+TOKEN+"&language="+LANG+"&page="+page;
        String json = getUrlContent(url);
        Result result = resultMapper(json);
        Movie movieFromResult = result.getResults().get(randomId);
        return getMovie(movieFromResult.getId());
    }
}
