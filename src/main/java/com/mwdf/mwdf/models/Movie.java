package com.mwdf.mwdf.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "movies")
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id")
    private Long idMovie;

    @NotNull
    @Column(name = "add_date", nullable = false)
    private Date addedAt;

    @NotNull
    @Column(name = "api_film_id", nullable = false)
    private int apiFilmId;

    @ManyToMany(mappedBy = "movies", fetch = FetchType.EAGER)
    Set<List> lists;

    public Long getIdMovie() {
        return idMovie;
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    public int getApiFilmId() {
        return apiFilmId;
    }

    public void setApiFilmId(int apiFilmId) {
        this.apiFilmId = apiFilmId;
    }

    public Set<List> getLists() {
        return lists;
    }

    public void setLists(Set<List> lists) {
        this.lists = lists;
    }
}
