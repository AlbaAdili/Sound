package com.example.sound.pojo.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull(message = "Name is required.")
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in the name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "genre_album",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    @JsonBackReference
    private Set<Album> albums;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "genre_song",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    @JsonBackReference
    private Set<Song> songs;

    public Genre() {}

    public Genre(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.albums = new HashSet<>();
        this.songs = new HashSet<>();
    }

    public Genre(Integer id, String name, Set<Album> albums, Set<Song> songs) {
        this.id = id;
        this.name = name;
        this.albums = albums;
        this.songs = songs;
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

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
        album.getGenres().add(this);
    }

    public void removeAlbum(Album album) {
        this.albums.remove(album);
        album.getGenres().remove(this);
    }

    public void addSong(Song song) {
        this.songs.add(song);
        song.getGenres().add(this);
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
        song.getGenres().remove(this);
    }
}
