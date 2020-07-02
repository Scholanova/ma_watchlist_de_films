package com.mwdf.mwdf.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mwdf.mwdf.entity.APIMovieDBAuthToken;


@Controller
public class UserController {
	
	@Value("${apidb.token}")
	private String TOKEN;
	
	@RequestMapping("/user/token")
	@ResponseBody
	public String getGenreMovieList() {
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
