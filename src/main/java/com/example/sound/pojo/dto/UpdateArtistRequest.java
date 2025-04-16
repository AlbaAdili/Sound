package com.example.sound.pojo.dto;
import java.util.Date;

public class UpdateArtistRequest {
    private String name;

    private String surname;

    private Date birthDate;

    private String country;

    private String city;

    private String biography;

    private Integer monthlyListeners;

    public UpdateArtistRequest() {}

    public UpdateArtistRequest(String name, String surname, Date birthDate, String country, String city, String biography, Integer monthlyListeners) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.country = country;
        this.city = city;
        this.biography = biography;
        this.monthlyListeners = 0;
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
}
