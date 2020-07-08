package com.mwdf.mwdf.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long idComment;

    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id") //quiz table will have a column `user_id` foreign key referring to user table's `id` column
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id") //quiz table will have a column `user_id` foreign key referring to user table's `id` column
    private Movie movie;

    public Comment(String content, User user, Movie movie) {
        this.content = content;
        this.user = user;
        this.movie = movie;
    }

    public Comment(String content) {
        this.content = content;
    }

    public Comment() {
    }

    public Long getIdComment() {
        return idComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
