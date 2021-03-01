package com.tomdenboer.composercloud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table
public class Song {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    String artist;

    @Column
    private String location;

    @ManyToOne
    @JsonBackReference
    private User user;


    public Song() {
    }

    public Song(String title, String location, User user) {
        this.title = title;
        this.location = location;
        this.user = user;
        this.artist = user.getUserName();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getArtist() { return artist; }

    public void setArtist(String artist) { this.artist = artist; }
}
