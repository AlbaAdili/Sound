package com.example.sound.controller;
import com.example.sound.pojo.dto.CreateGenreRequest;
import com.example.sound.pojo.dto.UpdateGenreRequest;
import com.example.sound.pojo.entity.Genre;
import com.example.sound.service.GenreService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping("/genres")
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @GetMapping("/genres/{id}")
    public Genre findOneById(@PathVariable @Positive(message = "Id must be a positive number") Integer id) {
        return genreService.findOneById(id);
    }

    @PostMapping("/genres")
    public Genre createGenre(@Valid @RequestBody CreateGenreRequest createGenreRequest) {
        return genreService.createGenre(createGenreRequest);
    }

    @DeleteMapping("/genres/{id}")
    public void deleteGenre(@PathVariable @Positive(message = "Id must be a positive number") Integer id) {
        genreService.deleteGenre(id);
    }

    @PutMapping("/genres/{id}")
    public Genre updateGenre(@PathVariable @Positive(message = "Id must be a positive number") Integer id, @Valid @RequestBody UpdateGenreRequest updateGenreRequest) {
        return genreService.updateGenre(id, updateGenreRequest);
    }
}
