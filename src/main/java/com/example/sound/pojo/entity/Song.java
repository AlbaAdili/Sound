package com.example.sound.pojo.entity;
import com.example.sound.converter.DurationConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull(message = "Name is required.")
    private String name;

    @Column(name = "duration")
    @Convert(converter = DurationConverter.class)
    private Duration duration;


    @Column(name = "plays")
    private Integer plays;

    @Column(name = "release_date")
    private Date releaseDate;

    @ManyToMany
    @JoinTable (
            name = "song_playlist",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id")
    )
    @JsonBackReference
    private Set<Playlist> playlists;

    @ManyToMany
    @JoinTable (
            name = "listening_history",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonBackReference
    private Set<User> users;

    @ManyToMany(mappedBy = "songs")
    private Set<Genre> genres;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonBackReference
    private Album album;

    public Song() {
        this.releaseDate = new Date();
    }

    public Song(Integer id, String name, Duration duration, Date releaseDate) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.plays = 0;
        this.releaseDate = releaseDate;
    }

    public Song(Integer id, String name, Duration duration, Date releaseDate, Set<Playlist> playlists, Set<User> users, Set<Genre> genres, Album album) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.plays = 0;
        this.releaseDate = releaseDate;
        this.playlists = playlists;
        this.users = users;
        this.genres = genres;
        this.album = album;
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

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setPlays(Integer plays) {
        this.plays = plays;
    }

    public Integer getPlays() {
        return plays;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addGenre(Genre genre) {
        if (genres == null) {
            genres = new HashSet<>();
        }
        genres.add(genre);
        genre.getSongs().add(this);
    }

    public void removeGenre(Genre genre) {
        if (genres != null) {
            genres.remove(genre);
            genre.getSongs().remove(this);
        }
    }
}
