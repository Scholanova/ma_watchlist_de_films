package com.mwdf.mwdf.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mwdf.mwdf.entity.APIMovieDBAuthToken;
import com.mwdf.mwdf.entity.Movie;
import com.mwdf.mwdf.entity.Result;
import com.mwdf.mwdf.services.MovieService;
import org.springframework.web.servlet.ModelAndView;


@Controller
@EnableAutoConfiguration
//@PropertySource("/application.properties")

public class MovieController {



	@Value("${apidb.token}")
	private String TOKEN;
	
	private String LANG = "fr-FR";
	private MovieService movieService;
	
	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	
	@RequestMapping("/api")
	@ResponseBody
	public String sayHelloAPI() {
		return "Hello World!!! API";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public String getMovieList() {
		return "";
	}



	@RequestMapping(value="/search",method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView searchMovies(@RequestParam String params, Model model) {
		Result res = movieService.searchMovies(params);
		model.addAttribute("movies", res.getResults());
		return new ModelAndView("index");

	}

	@RequestMapping("/movie/{id}")
	@ResponseBody
	public String getMovie(@PathVariable("id") int movieId) {
		String url = "https://api.themoviedb.org/3/movie/"+ movieId+"?api_key="+TOKEN+"&language="+LANG;		
		/*
		Gson g = new Gson();
		
		Movie outputMovie = new Gson().fromJson(sb.toString(), Movie.class);
		outputMovie.toString();
		String serializedMovie = new Gson().toJson(outputMovie.toString());
		return serializedMovie;
		*/
		String jsonMovie = getUrlContent(url);
		//Jackson does't ignore what is not in class
		//Movie m = new Gson().fromJson(json, Movie.class);
		Movie m2 = new Movie();
		
	

		try {
			ObjectMapper mapper = new ObjectMapper();
			//Jackson does't ignore what is not in entity by default need to use below line, can also use annotation in entity class
			//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			m2 = mapper.readValue(jsonMovie, Movie.class);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m2.toString();


		//return getUrlContent(movie);


	}
	

	
	@RequestMapping("/genre/movie/list")
	@ResponseBody
	public String getGenreMovieList() {
		String url = "https://api.themoviedb.org/3/genre/movie/list?api_key="+TOKEN+"&language="+LANG;
		return getUrlContent(url);
	}
	
	@RequestMapping("/user/token")
	@ResponseBody
	public String getApiDbToken() {
		String url = "https://api.themoviedb.org/3/authentication/token/new?api_key="+TOKEN;
		String jsonMovie = getUrlContent(url);
		
		APIMovieDBAuthToken apidbtoken = new APIMovieDBAuthToken();
		ObjectMapper mapper = new ObjectMapper();
		try {
			apidbtoken = mapper.readValue(jsonMovie, APIMovieDBAuthToken.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonMovie;
		//return apidbtoken.toString();
	}
	@RequestMapping("/movie/random")
	@ResponseBody
	public String randomMovie(Model model) {
		Movie movie = movieService.getRandomMovie();
		model.addAttribute("movie", movie);
		return model.toString();
	}
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
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
