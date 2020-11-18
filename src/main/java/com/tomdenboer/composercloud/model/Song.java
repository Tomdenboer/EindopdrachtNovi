package com.tomdenboer.composercloud.model;

import javax.persistence.*;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String artist;

    @Column
    private String name;

    @Column
    private String location;

//    @ManyToOne
//    private User user;


    public Song() {

    }
//    public Song(long id, String artist, String name, String location, User user) {
//        this.id = id;
//        this.artist = artist;
//        this.name = name;
//        this.location = location;
//        this.user = user;
//    }


    public Song(String artist, String name, String location) {
        this.artist = artist;
        this.name = name;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
