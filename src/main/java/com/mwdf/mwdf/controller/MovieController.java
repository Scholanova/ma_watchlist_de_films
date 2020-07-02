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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mwdf.mwdf.entity.Movie;

@Controller
@EnableAutoConfiguration
//@PropertySource("/application.properties")

public class MovieController {

	@Value("${apidb.token}")
	private String TOKEN;
	
	private String LANG = "fr-FR";

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
	


	@RequestMapping("/search/{params}")
	@ResponseBody
	public String searchMovie(@PathVariable("params") String params) {
		String url = "https://api.themoviedb.org/3/search/movie?api_key="+TOKEN+"&query="+params;
		return getUrlContent(url);
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
