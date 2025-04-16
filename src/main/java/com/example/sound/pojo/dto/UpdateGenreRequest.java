package com.example.sound.pojo.dto;

public class UpdateGenreRequest {
    private String name;

    public UpdateGenreRequest() {}

    public UpdateGenreRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
