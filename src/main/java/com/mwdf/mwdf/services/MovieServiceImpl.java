package com.mwdf.mwdf.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mwdf.mwdf.entity.Movie;
import com.mwdf.mwdf.exception.MovieException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieServiceImpl implements MovieService{

    @Value("${apidb.token}")
    private String TOKEN;



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
