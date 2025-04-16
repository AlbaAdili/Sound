package com.example.sound.pojo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotNull(message = "Name is required.")
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in the name")
    private String name;

    @Column(name = "surname")
    @NotNull(message = "Surname is required.")
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in the surname")
    private String surname;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    @NotNull(message = "Birth date is required.")
    private Date birthDate;

    @Column(name = "country")
    @NotNull(message = "Country is required.")
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in the country")
    private String country;

    @Column(name = "city")
    @NotNull(message = "City is required.")
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in the city")
    private String city;

    @OneToMany(mappedBy = "user")
    private Set<Playlist> playlists;

    @ManyToMany(mappedBy = "users")
    private Set<Song> songs;

    public User() {}

    public User(Integer id, String name, String surname, Date birthDate, String country, String city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.country = country;
        this.city = city;
    }

    public User(Integer id, String name, String surname, Date birthDate, String country, String city, Set<Playlist> playlists, Set<Song> songs) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.country = country;
        this.city = city;
        this.playlists = playlists;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
