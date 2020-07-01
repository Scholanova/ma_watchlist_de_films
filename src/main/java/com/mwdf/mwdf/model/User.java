package com.mwdf.mwdf.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="users")
public class User {
    @Id
    private int id;
    private String name;
    private String email;

    public User(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
