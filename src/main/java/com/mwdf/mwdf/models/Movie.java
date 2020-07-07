package com.mwdf.mwdf.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long idMovie;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "add_date", nullable = false)
    private Date addedAt = new Date();

    @NotNull
    @Column(name = "api_film_id", nullable = false)
    private int apiFilmId;

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
}
