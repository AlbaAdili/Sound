package com.example.sound.pojo.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull(message = "Name is required.")
    private String name;

    @Column(name = "description")
    @NotNull(message = "Description is required.")
    private String description;

    @ManyToMany(mappedBy = "playlists")
    private Set<Song> songs;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Playlist() {}

    public Playlist(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Playlist(Integer id, String name, String description, Set<Song> songs, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.songs = songs;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
