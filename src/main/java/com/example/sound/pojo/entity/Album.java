package com.example.sound.pojo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    @NotNull(message = "Title is required.")
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in the title")
    private String title;

    @Column(name = "release_date")
    @NotNull(message = "Release date is required.")
    private Date releaseDate;

    @ManyToMany(mappedBy = "albums")
    private Set<Genre> genres;

    @ManyToMany(mappedBy = "albums")
    private Set<Artist> artists;

    @OneToMany(mappedBy = "album")
    private Set<Song> songs;

    public Album() {
        this.releaseDate = new Date();
        this.artists = new HashSet<>();
        this.genres = new HashSet<>();
    }

    public Album(Integer id, String title, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public Album(Integer id, String title, Date releaseDate, Set<Genre> genres, Set<Artist> artists, Set<Song> songs) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.artists = artists;
        this.songs = songs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public void addGenre(Genre genre) {
        if (genres == null) {
            genres = new HashSet<>();
        }
        genres.add(genre);
        genre.getAlbums().add(this);
    }

    public void removeGenre(Genre genre) {
        if (genres != null) {
            genres.remove(genre);
            genre.getAlbums().remove(this);
        }
    }
}
