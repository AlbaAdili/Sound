package com.example.sound.pojo.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import java.util.Date;

public class CreateAlbumRequest {
    @NotNull(message = "Title is required.")
    @Pattern(regexp = "[A-Za-z.\\s]+", message = "Enter valid characters in the title")
    private String title;

    @NotNull(message = "Release date is required.")
    private Date releaseDate;

    private List<String> genreNames;

    public CreateAlbumRequest() {
        this.releaseDate = new Date();
    }

    public CreateAlbumRequest(String title, Date releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getGenreNames() {
        return genreNames;
    }

    public void setGenreNames(List<String> genreNames) {
        this.genreNames = genreNames;
    }
}
