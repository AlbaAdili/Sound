package com.example.sound.pojo.dto;
import java.util.List;

public class UpdateAlbumRequest {
    private String title;

    private List<String> genreNames;

    public UpdateAlbumRequest() {}

    public UpdateAlbumRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenreNames() {
        return genreNames;
    }

    public void setGenreNames(List<String> genreNames) {
        this.genreNames = genreNames;
    }
}
