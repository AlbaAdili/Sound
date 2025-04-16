package com.example.sound.pojo.dto;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class CreateSongRequest {
    @NotNull(message = "Name is required.")
    private String name;

    @NotNull(message = "Duration is required.")
    private Duration duration;

    private Integer plays;

    private Date releaseDate;

    @NotNull(message = "Id of album is required.")
    private Integer albumId;

    private List<String> genreNames;

    public CreateSongRequest() {}

    public CreateSongRequest(String name, Duration duration, Date releaseDate) {
        this.name = name;
        this.duration = duration;
        this.plays = 0;
        this.releaseDate = releaseDate;
    }

    public CreateSongRequest(String name, Duration duration, Date releaseDate, Integer albumId, List<String> genreNames) {
        this.name = name;
        this.duration = duration;
        this.plays = 0;
        this.releaseDate = releaseDate;
        this.albumId = albumId;
        this.genreNames = genreNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Integer getPlays() {
        return plays;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public List<String> getGenreNames() {
        return genreNames;
    }

    public void setGenreNames(List<String> genreNames) {
        this.genreNames = genreNames;
    }
}
