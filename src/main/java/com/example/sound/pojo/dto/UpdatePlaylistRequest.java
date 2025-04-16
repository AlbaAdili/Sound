package com.example.sound.pojo.dto;

public class UpdatePlaylistRequest {
    private String name;

    private String description;

    public UpdatePlaylistRequest() {}

    public UpdatePlaylistRequest(String name, String description) {
        this.name = name;
        this.description = description;
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
}
