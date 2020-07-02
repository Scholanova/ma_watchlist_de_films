package com.mwdf.mwdf.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Movie {
	
	private boolean adult;
	private long budget;
	private int id;
	private String imdb_id;
	private String original_language;
	private String original_title;
	private String overview;
	private double popularity;
	
	//JSON
	
	public Movie() {
		
	}
	
	public Movie(boolean adult, long budget, int id, String imdb_id, String original_language, String original_title,
			String overview, double popularity) {
		super();
		this.adult = adult;
		this.budget = budget;
		this.id = id;
		this.imdb_id = imdb_id;
		this.original_language = original_language;
		this.original_title = original_title;
		this.overview = overview;
		this.popularity = popularity;
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
	@Override
	public String toString() {
		return "Movie [adult=" + adult + ", budget=" + budget + ", id=" + id + ", imdb_id=" + imdb_id
				+ ", original_language=" + original_language + ", original_title=" + original_title + ", overview="
				+ overview + ", popularity=" + popularity + "]";
	}
	
}
