package com.mwdf.mwdf.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id")
    private Long idMovie;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "add_date", nullable = false)
    private Date addedAt = new Date();

    @NotNull
    @Column(name = "api_film_id", nullable = false)
    private int apiFilmId;

    @NotNull
    @Column(name = "already_seen", nullable = false, columnDefinition = "boolean default false")
    private boolean alreadySeen = false;

    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    @Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    Set<CustomList> lists = new HashSet<>();

    public Long getIdMovie() {
        return idMovie;
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public int getApiFilmId() {
        return apiFilmId;
    }

    public void setApiFilmId(int apiFilmId) {
        this.apiFilmId = apiFilmId;
    }

    public Set<CustomList> getLists() {
        return lists;
    }

    public void setLists(Set<CustomList> lists) {
        this.lists = lists;
    }

    public Movie() {}

    public Movie(int apiFilmId) {
        this.apiFilmId = apiFilmId;
    }

    @OneToMany(mappedBy="movie")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(value = {org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.REMOVE})
    private List<Comment> comment = new ArrayList<>();

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public boolean isAlreadySeen() {
        return alreadySeen;
    }

    public void setAlreadySeen(boolean alreadySeen) {
        this.alreadySeen = alreadySeen;
    }
}
