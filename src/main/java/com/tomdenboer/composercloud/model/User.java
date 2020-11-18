package com.tomdenboer.composercloud.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column()
    private String userName;

    @Column
    private String password;

    @Column
    private boolean active;

//    @Column
//    private String roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
                joinColumns = @JoinColumn(name = "user_id", nullable = false),
                inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private Set<Role> userRoles;

//    @OneToMany(mappedBy = "user")
//    @Column
//    private List<Song> songs;

    public User(){
    }

    public User(long id, String userName, String password, boolean active, Set<Role> roles, List<Song> songs) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.userRoles = roles;
//        this.songs = songs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return userRoles;
    }

    public void setRole(Set<Role> roles) {
        this.userRoles = roles;
    }

//    public List<Song> getSongs() {
//        return songs;
//    }
//
//    public void setSongs(List<Song> songs) {
//        this.songs = songs;
//    }
}
