package com.mwdf.mwdf.controller;

import com.mwdf.mwdf.entity.Movie;
import com.mwdf.mwdf.models.User;
import com.mwdf.mwdf.repositories.UserRepository;
import com.mwdf.mwdf.services.MovieService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class MainController {

	private MovieService movieService;
	private UserRepository userRepository;

	public MainController(MovieService movieService, UserRepository userRepository) {
		this.movieService = movieService;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/")
	public String accueilPage(Model model)
	{
		Movie movie = movieService.getRandomMovie();
		model.addAttribute("movie", movie);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();

			User user = userRepository.findByUsername(currentUserName);
			model.addAttribute("lists", user.getLists());
			if ( user.getLists().size()== 0)
				model.addAttribute("redirect",0);
			else
				model.addAttribute("redirect",1);
		}
		else
			model.addAttribute("redirect",2);


		return "index";
	}
}
