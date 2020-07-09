package com.mwdf.mwdf.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mwdf.mwdf.models.Comment;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Movie {
	

	private boolean adult;
	private long budget;
	private int id;
	private List<Comment> comments;
	private String imdb_id;
	private String original_language;
	private String original_title;
	private String overview;
	private double popularity;
	private String poster_path;
	private String backdrop_path;
	private String posterFilm;
	private String poster2Film;
	private int position;
	private BelongsToCollection belongs_to_collection;
	private List<Genre> genres;
	private List <SpokenLanguages> spoken_languages;
	private String status;
	private String tagline;
	private String title;
	private boolean video;
	private double vote_average;
	private int vote_count;
	private int runtime;
	private boolean alreadySeen;

	private String release_date;

	public Movie() {
		
	}
	
	public Movie(boolean adult, long budget, int id, String imdb_id, String original_language, String original_title,
				 String overview, double popularity, String poster_path, String backdrop_path) {
		super();
		this.adult = adult;
		this.budget = budget;
		this.id = id;
		this.imdb_id = imdb_id;
		this.original_language = original_language;
		this.original_title = original_title;
		this.overview = overview;
		this.popularity = popularity;
		this.poster_path = poster_path;
		this.backdrop_path = backdrop_path;
		this.initPosterFilm();
	}
	
	public boolean isAdult() {
		return adult;
	}
	public void setAdult(boolean adult) {
		this.adult = adult;
	}
	public long getBudget() {
		return budget;
	}
	public void setBudget(long budget) {
		this.budget = budget;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImdb_id() {
		return imdb_id;
	}
	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}
	public String getOriginal_language() {
		return original_language;
	}
	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}
	public String getOriginal_title() {
		return original_title;
	}
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public double getPopularity() {
		return popularity;
	}
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getBackdrop_path() {
		return backdrop_path;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public boolean isAlreadySeen() {
		return alreadySeen;
	}

	public void setAlreadySeen(boolean alreadySeen) {
		this.alreadySeen = alreadySeen;
	}

	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}
	public String getPosterFilm() {

		return this.posterFilm;
	}

	public void setPosterFilm(String s){
		this.posterFilm = s;
	}

	public int getPosition(){
		return this.position;
	}

	public void setPosition(int p){
		this.position = p;
	}

	public BelongsToCollection getBelongs_to_collection() {
		return belongs_to_collection;
	}

	public void setBelongs_to_collection(BelongsToCollection belongs_to_collection) {
		this.belongs_to_collection = belongs_to_collection;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List <SpokenLanguages> getSpoken_languages() {
		return spoken_languages;
	}

	public void setSpoken_languages(List <SpokenLanguages> spoken_languages) {
		this.spoken_languages = spoken_languages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isVideo() {
		return video;
	}

	public void setVideo(boolean video) {
		this.video = video;
	}

	public double getVote_average() {
		return vote_average;
	}

	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}

	public int getVote_count() {
		return vote_count;
	}

	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getPoster2Film() {
		return poster2Film;
	}

	public void setPoster2Film(String poster2Film) {
		this.poster2Film = poster2Film;
	}

	@Override
	public String toString() {
		return "Movie{" +
				"adult=" + adult +
				", budget=" + budget +
				", id=" + id +
				", imdb_id='" + imdb_id + '\'' +
				", original_language='" + original_language + '\'' +
				", original_title='" + original_title + '\'' +
				", overview='" + overview + '\'' +
				", popularity=" + popularity +
				", poster_path='" + poster_path + '\'' +
				", backdrop_path='" + backdrop_path + '\'' +
				", posterFilm='" + posterFilm + '\'' +
				", poster2Film='" + poster2Film + '\'' +
				", position=" + position +
				", belongs_to_collection=" + belongs_to_collection +
				", genres=" + genres +
				", spoken_languages=" + spoken_languages +
				", status='" + status + '\'' +
				", tagline='" + tagline + '\'' +
				", title='" + title + '\'' +
				", video=" + video +
				", vote_average=" + vote_average +
				", vote_count=" + vote_count +
				", runtime=" + runtime +
				", release_date='" + release_date + '\'' +
				'}';
	}

	public void initPosterFilm(){
		this.posterFilm = "https://image.tmdb.org/t/p/original" + this.getPoster_path();
	}

	public void initBackDropFilm(){
		this.poster2Film = "https://image.tmdb.org/t/p/original" + this.getBackdrop_path();
	}

	public String getPosterPathUrl(){
		if(this.getPoster_path() == null){
			//return "https://upload.wikimedia.org/wikipedia/commons/e/e6/Pas_d%27image_disponible.svg";
			return null;
		}
		else{
			return "https://image.tmdb.org/t/p/original" + this.getPoster_path();
		}
	}

	public String getBackdropPathUrl(){
		if(this.getBackdrop_path() == null){
			return null;
		}
		else{
			return "https://image.tmdb.org/t/p/original" + this.getBackdrop_path();
		}
	}

	public void initGenreIfNull(){
		if(this.genres == null || this.genres.size() ==0){
			this.genres = new ArrayList<Genre>();
			this.genres.add(new Genre(-1, "No Genre"));
		}
	}

	public void initBelongs_To_CollentionIfNull(){
		if(this.belongs_to_collection == null){
			this.belongs_to_collection = new BelongsToCollection(-1, "No Collection", "", "");
		}
	}

	public void initSpoken_languagesIfNull(){
		if(this.spoken_languages == null || this.spoken_languages.size()==0){
			this.spoken_languages = new ArrayList<SpokenLanguages>();
			this.spoken_languages.add(new SpokenLanguages("-1", "No Spoken Language"));
		}
	}
}
