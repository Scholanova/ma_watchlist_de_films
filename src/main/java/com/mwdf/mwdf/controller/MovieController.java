package com.mwdf.mwdf.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mwdf.mwdf.model.Movie;

@Controller
@EnableAutoConfiguration
public class MovieController {
	
	private static final String TOKEN = "63f6e155128296e4a9dec2c4450b44a5";
	
	@RequestMapping("/api")
	@ResponseBody
	public String sayHelloAPI() {
		return "Hello World!!! API";
	}
	
	@RequestMapping("/movie")
	@ResponseBody
	public String getMovie() {
		String movie = "https://api.themoviedb.org/3/movie/500?api_key="+TOKEN+"&language=fr-FR\r\n" + 
				"";
		StringBuilder sb = new StringBuilder();
		try {

			
	        URL url = new URL(movie);
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
		
		
		
		Gson g = new Gson();
		

		
		Movie outputMovie = new Gson().fromJson(sb.toString(), Movie.class);
		outputMovie.toString();
		String serializedMovie = new Gson().toJson(outputMovie.toString());
		//return serializedMovie;
		

		return sb.toString();

	}
}
