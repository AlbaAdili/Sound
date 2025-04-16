package com.example.sound.pojo.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "artist")
public class Artist {
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
    @JsonFormat(pattern = "yyyy-MM-dd")
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

    @Column(name = "biography")
    @NotNull(message = "Biography is required.")
    private String biography;

    @Column(name = "monthly_listeners")
    private Integer monthlyListeners;

    @ManyToMany
    @JoinTable(
            name = "artist_album",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    @JsonBackReference
    private Set<Album> albums;

    public Artist() {}

    public Artist(Integer id, String name, String surname, Date birthDate, String country, String city, String biography) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.country = country;
        this.city = city;
        this.biography = biography;
        this.monthlyListeners = 0;
    }

    public Artist(Integer id, String name, String surname, Date birthDate, String country, String city, String biography, Set<Album> albums) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.country = country;
        this.city = city;
        this.biography = biography;
        this.monthlyListeners = 0;
        this.albums = albums;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Integer getMonthlyListeners() {
        return monthlyListeners;
    }

    public void setMonthlyListeners(Integer monthlyListeners) {
        this.monthlyListeners = monthlyListeners;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
        album.getArtists().add(this);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
        album.setArtists(new HashSet<>());
    }
}
