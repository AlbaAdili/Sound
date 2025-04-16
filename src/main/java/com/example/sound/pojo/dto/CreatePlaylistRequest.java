package com.example.sound.pojo.dto;
import jakarta.validation.constraints.NotNull;

public class CreatePlaylistRequest {
    @NotNull(message = "Name is required.")
    private String name;

    @NotNull(message = "Description is required.")
    private String description;

    @NotNull(message = "Id of user is required.")
    private Integer userId;

    public CreatePlaylistRequest() {}

    public CreatePlaylistRequest(String name, String description) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
