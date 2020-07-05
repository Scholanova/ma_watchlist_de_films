package com.mwdf.mwdf.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lists")
@Data
public class CustomList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "list_id")
    private Long idList;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "movieslists",
        joinColumns = @JoinColumn(name = "list_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id", nullable = true))
    Set<Movie> movies;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "userslists",
        joinColumns = @JoinColumn(name = "list_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false))
    Set<User> users;

    public Long getIdList() {
        return idList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.getUsers().add(user);
    }

    public void addMovie(Movie movie) {
        this.getMovies().add(movie);
    }

    public CustomList() {
    }

    public CustomList(String title) {
        this.title = title;
    }

    public CustomList(String title, Set<Movie> movies, Set<User> users) {
        this.title = title;
        this.movies = movies;
        this.users = users;
    }
}
