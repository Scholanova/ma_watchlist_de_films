package com.mwdf.mwdf.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mwdf.mwdf.model.Movie;

@Controller
@EnableAutoConfiguration
//@PropertySource("/application.properties")

public class MovieController {

	@Value("${apidb.token}")
	private String TOKEN;
	
	@Value("${apidb.language}")
	private String LANG;

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
		String movie = "https://api.themoviedb.org/3/movie/"+ movieId+"?api_key="+TOKEN+"&language="+LANG;
				
		/*
		Gson g = new Gson();
		
		Movie outputMovie = new Gson().fromJson(sb.toString(), Movie.class);
		outputMovie.toString();
		String serializedMovie = new Gson().toJson(outputMovie.toString());
		return serializedMovie;
		*/

		return getUrlContent(movie);

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
}
