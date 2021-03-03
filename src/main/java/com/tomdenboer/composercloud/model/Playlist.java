package com.tomdenboer.composercloud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "playlist_song",
                joinColumns = @JoinColumn(name = "playlist_id", nullable = false),
                inverseJoinColumns = @JoinColumn(name = "song_id", nullable = true))
    private Set<Song> songs;

    public Playlist() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
