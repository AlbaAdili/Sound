package com.example.sound.service;
import com.example.sound.pojo.dto.CreateGenreRequest;
import com.example.sound.pojo.dto.UpdateGenreRequest;
import com.example.sound.pojo.entity.Genre;
import java.util.List;

public interface GenreService {
    List<Genre> findAll();
    Genre findOneById(Integer id);
    Genre createGenre(CreateGenreRequest createGenreRequest);
    void deleteGenre(Integer id);
    Genre updateGenre(Integer id, UpdateGenreRequest updateGenreRequest);
}
