package com.mwdf.mwdf.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.*;
import java.util.stream.Collectors;


import com.mwdf.mwdf.entity.Genre;
import com.mwdf.mwdf.models.Comment;
import com.mwdf.mwdf.models.CustomList;
import com.mwdf.mwdf.models.User;
import com.mwdf.mwdf.repositories.CommentRepository;
import com.mwdf.mwdf.repositories.CustomListRepository;
import com.mwdf.mwdf.repositories.MovieRepository;
import com.mwdf.mwdf.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	private UserRepository userRepository;
	private CustomListRepository customListRepository;
	private MovieRepository movieRepository;
	private CommentRepository commentRepository;

	public MovieController(MovieService movieService, MovieRepository movieRepository, CustomListRepository customListRepository, UserRepository userRepository, CommentRepository commentRepository) {
		super();
		this.movieService = movieService;
		this.customListRepository = customListRepository;
		this.userRepository = userRepository;
		this.movieRepository = movieRepository;
		this.commentRepository = commentRepository;
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

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();

			User user = userRepository.findByUsername(currentUserName);

			Set<CustomList> lists = user.getLists();
			Set<CustomList> sortedList = lists.stream()
					.sorted(Comparator.comparing(CustomList::getTitle)).collect(Collectors.toCollection(LinkedHashSet::new));

			model.addAttribute("lists",sortedList);
			if ( user.getLists().size()== 0)
				model.addAttribute("redirect",0);
			else
				model.addAttribute("redirect",1);

		}
		else
			model.addAttribute("redirect",2);
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

	@PostMapping("/movie_to_list")
	public ModelAndView addMovieToAList( @RequestParam Map<String, String> queryMap,@RequestParam("apiFilmId") int apiFilmId) {
		String listId = "";
		for (Map.Entry<String, String> entry : queryMap.entrySet()) {
			String k = entry.getKey();
			if (k.equals("idList")) {
				listId = entry.getValue().substring(4);
			}
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			com.mwdf.mwdf.models.Movie movie = new com.mwdf.mwdf.models.Movie(apiFilmId);
			CustomList list = customListRepository.findByIdList( Long.parseLong(listId));
			list.getMovies().add(movie);
			customListRepository.save(list);
			return new ModelAndView("redirect:" + "/");
		}
		return new ModelAndView("redirect:" + "/connexion");
	}

	@GetMapping("/list/{listTitle}_{listId}")
	public String getMoviesFromIds(@PathVariable("listId") long listId, @PathVariable("listTitle") String listTitle, Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			CustomList list = customListRepository.findByIdList(listId);
			List<Movie> movies = new ArrayList<Movie>();

			for (com.mwdf.mwdf.models.Movie movie : list.getMovies()) {
				if (!movie.isAlreadySeen()) {
					Movie apiMovie = movieService.getMovie(movie.getApiFilmId());
					apiMovie.setComments(movie.getComment());
					apiMovie.setAlreadySeen(movie.isAlreadySeen());
					movies.add(apiMovie);
				}
			}

			model.addAttribute("movies", movies);
			model.addAttribute("listTitle", listTitle);
			model.addAttribute("idList", listId);

			return "lists/myList";
		}

		return "connexion/connexion";
	}

	@GetMapping("/genres")
	@ResponseBody
	public String getGenres(){

		return String.join(", ", movieService.getAllGenre().toString());
	}
	@GetMapping("/randomGenre")
	@ResponseBody
	public String getRandom(@RequestParam("idGenre") int idGenre) {

		return movieService.getRandomMovieGenre(idGenre).toString();
	}

	@GetMapping("/delete_movie")
	public ModelAndView deleteMovieFromList(@RequestParam int movieId, @RequestParam long listId){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			CustomList list = customListRepository.findByIdList(listId);
			List<com.mwdf.mwdf.models.Movie> movieList = movieRepository.findByApiFilmIdAndLists(movieId, list);

			com.mwdf.mwdf.models.Movie movie = movieList.stream().findFirst().get();
			movie.getLists().removeIf(m -> m.getIdList().equals(listId));
			list.getMovies().removeIf(l -> l.getIdMovie().equals(movie.getIdMovie()));

			movieRepository.save(movie);
			customListRepository.save(list);

			return new ModelAndView("redirect:" + "/list/" + list.getTitle() + "_" + listId);
		}

		return new ModelAndView("connexion/connexion");
	}

	@PostMapping("/add_comment")
	public ModelAndView PostCommentToMovie(@RequestParam String commentContent, @RequestParam int idMovie, @RequestParam long listId){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String userName = authentication.getName();

			User currentUser = userRepository.findByUsername(userName);
			CustomList list = customListRepository.findByIdList(listId);
			List<com.mwdf.mwdf.models.Movie> movieList = movieRepository.findByApiFilmIdAndLists(idMovie, list);

			for (com.mwdf.mwdf.models.Movie movie: movieList) {
				Comment comment = new Comment(commentContent);
				comment.setUser(currentUser);
				comment.setMovie(movie);
				commentRepository.save(comment);
			}

			return new ModelAndView("redirect:" + "/list/" + list.getTitle() + "_" + listId);
		}

		return new ModelAndView("connexion/connexion");
	}

	@GetMapping("/delete_comment")
	public ModelAndView DeleteComment(@RequestParam long commentId, @RequestParam long listId, @RequestParam int idMovie){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String userName = authentication.getName();
			User currentUser = userRepository.findByUsername(userName);
			CustomList list = customListRepository.findByIdList(listId);
			List<com.mwdf.mwdf.models.Movie> movies = movieRepository.findByApiFilmIdAndLists(idMovie, list);

			for (com.mwdf.mwdf.models.Movie movie: movies) {
				List<Comment> comments = commentRepository.findByUserAndMovie(currentUser, movie);
				comments.removeIf(m -> m.getIdComment().equals(commentId));
				movie.setComment(comments);
				currentUser.setComment(comments);
			}
			commentRepository.deleteById(commentId);


			return new ModelAndView("redirect:" + "/list/" + list.getTitle() + "_" + listId);
		}

		return new ModelAndView("connexion/connexion");
	}


	@GetMapping("/add_movie_to_seen")
	public ModelAndView MovieToSeen(@RequestParam long listId, @RequestParam int idMovie){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String userName = authentication.getName();
			User currentUser = userRepository.findByUsername(userName);
			CustomList list = customListRepository.findByIdList(listId);

			List<CustomList> customLists = customListRepository.findByUsers(currentUser);

			for (CustomList customList : customLists) {
				for (com.mwdf.mwdf.models.Movie movie : customList.getMovies()) {
					if (movie.getApiFilmId() == idMovie) {
						movie.setAlreadySeen(true);
						movieRepository.save(movie);
					}
				}
			}

			return new ModelAndView("redirect:" + "/list/" + list.getTitle() + "_" + listId);
		}

		return new ModelAndView("connexion/connexion");
	}

	@GetMapping("/mes_films_vus")
	public ModelAndView GetSeenMovies(Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String userName = authentication.getName();
			User currentUser = userRepository.findByUsername(userName);

			List<CustomList> customLists = customListRepository.findByUsers(currentUser);

			List<com.mwdf.mwdf.models.Movie> moviesSeen = new ArrayList<>();
			for (CustomList customList : customLists) {
				for (com.mwdf.mwdf.models.Movie movie : customList.getMovies()) {
					if (movie.isAlreadySeen()) {
						moviesSeen.add(movie);
					}
				}
			}

			List<Movie> apiMoviesSeen = new ArrayList<>();
			for (com.mwdf.mwdf.models.Movie movie : moviesSeen) {
				Movie apiMovie = movieService.getMovie(movie.getApiFilmId());
				apiMovie.setComments(movie.getComment());
				apiMoviesSeen.add(apiMovie);
			}

			model.addAttribute("movies", apiMoviesSeen);
			return new ModelAndView("movies/movieSeen");
		}

		return new ModelAndView("connexion/connexion");
	}

	@PostMapping("/add_comment_to_seen_movie")
	public ModelAndView PostCommentToMovieSeenMovie(@RequestParam String commentContent, @RequestParam int idMovie){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String userName = authentication.getName();

			User currentUser = userRepository.findByUsername(userName);
			List<CustomList> customLists = customListRepository.findByUsers(currentUser);

			for (CustomList customList : customLists) {
				for (com.mwdf.mwdf.models.Movie movie : customList.getMovies()) {
					if (movie.getApiFilmId() == idMovie) {
						Comment comment = new Comment(commentContent);
						comment.setUser(currentUser);
						comment.setMovie(movie);
						commentRepository.save(comment);
					}
				}
			}

			return new ModelAndView("redirect:" + "/mes_films_vus");
		}

		return new ModelAndView("connexion/connexion");
	}

	@GetMapping("/delete_comment_already_seen_movie")
	public ModelAndView DeleteComment(@RequestParam long commentId, @RequestParam int idMovie){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String userName = authentication.getName();
			User currentUser = userRepository.findByUsername(userName);

			List<CustomList> customLists = customListRepository.findByUsers(currentUser);

			for (CustomList customList : customLists) {
				for (com.mwdf.mwdf.models.Movie movie : customList.getMovies()) {
					if (movie.getApiFilmId() == idMovie) {
						List<Comment> comments = commentRepository.findByUserAndMovie(currentUser, movie);
						comments.removeIf(m -> m.getIdComment().equals(commentId));
						movie.setComment(comments);
						currentUser.setComment(comments);
					}
				}
			}
			commentRepository.deleteById(commentId);

			return new ModelAndView("redirect:" + "/mes_films_vus");
		}

		return new ModelAndView("connexion/connexion");
	}

	@PostMapping("/remove_already_seen")
	public ModelAndView RemoveAlreadySeen(@RequestParam int idMovie){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String userName = authentication.getName();
			User currentUser = userRepository.findByUsername(userName);
			List<CustomList> customLists = customListRepository.findByUsers(currentUser);

			for (CustomList customList : customLists) {
				for (com.mwdf.mwdf.models.Movie movie : customList.getMovies()) {
					if (movie.getApiFilmId() == idMovie) {
						movie.setAlreadySeen(false);
						movieRepository.save(movie);
					}
				}
			}

			return new ModelAndView("redirect:" + "/mes_films_vus");
		}

		return new ModelAndView("connexion/connexion");
	}
}
