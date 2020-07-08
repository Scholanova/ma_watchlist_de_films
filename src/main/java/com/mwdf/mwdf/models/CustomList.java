package com.mwdf.mwdf.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lists")
public class CustomList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "list_id")
    private Long idList;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    @JoinTable(
        name = "movieslists",
        joinColumns = @JoinColumn(name = "list_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id", nullable = true))
    Set<Movie> movies  =  new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    @JoinTable(
        name = "userslists",
        joinColumns = @JoinColumn(name = "list_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false))
    Set<User> users =  new HashSet<>();

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

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public CustomList() {
    }

    public CustomList(String title) {
        this.title = title;
    }

    public CustomList(long id, String title) {
        this.idList = id;
        this.title = title;
    }

    public CustomList(String title, Set<Movie> movies, Set<User> users) {
        this.title = title;
        this.movies = movies;
        this.users = users;
    }
}
