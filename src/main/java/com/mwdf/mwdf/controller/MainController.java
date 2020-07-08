package com.mwdf.mwdf.controller;

import com.mwdf.mwdf.entity.Movie;
import com.mwdf.mwdf.services.MovieService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class MainController {

	private MovieService movieService;

	public MainController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping("/")
	public String accueilPage(Model model)
	{
		//test
		Movie movie = movieService.getRandomMovie();
		model.addAttribute("movie", movie);
		return "index";
	}
}
