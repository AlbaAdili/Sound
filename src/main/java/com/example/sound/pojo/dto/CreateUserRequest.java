package com.example.sound.pojo.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

public class CreateUserRequest {
    @NotNull(message = "Name is required.")
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in the name")
    private String name;

    @NotNull(message = "Surname is required.")
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in the surname")
    private String surname;

    @NotNull(message = "Birth date is required.")
    private Date birthDate;

    @NotNull(message = "Country is required.")
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in the country")
    private String country;

    @NotNull(message = "City is required.")
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in the city")
    private String city;

    public CreateUserRequest() {}

    public CreateUserRequest(String name, String surname, Date birthDate, String country, String city) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.country = country;
        this.city = city;
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
}
