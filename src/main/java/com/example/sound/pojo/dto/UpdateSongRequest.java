package com.example.sound.pojo.dto;
import java.util.List;

public class UpdateSongRequest {
    private String name;
    private Integer plays;
    private List<String> genreNames;
    public UpdateSongRequest() {}

    public UpdateSongRequest(String name, Integer plays) {
        this.name = name;
        this.plays = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlays() {
        return plays;
    }

    public List<String> getGenreNames() {
        return genreNames;
    }

    public void setGenreNames(List<String> genreNames) {
        this.genreNames = genreNames;
    }
}
