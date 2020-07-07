package com.mwdf.mwdf.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BelongsToCollection {
    private int id;
    private String name;
    private String post_path;
    private String backdrop_path;

    public BelongsToCollection(){

    }
    public BelongsToCollection(int id, String name, String post_path, String backdrop_path) {
        this.id = id;
        this.name = name;
        this.post_path = post_path;
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost_path() {
        return post_path;
    }

    public void setPost_path(String post_path) {
        this.post_path = post_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    @Override
    public String toString() {
        return "BelongsToCollection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", post_path='" + post_path + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                '}';
    }
}
