package com.example.sound.controller;
import com.example.sound.pojo.dto.*;
import com.example.sound.pojo.entity.Artist;
import com.example.sound.service.ArtistService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping("/artists")
    public List<Artist> findAll() {
        return artistService.findAll();
    }

    @GetMapping("/artists/{id}")
    public Artist findOneById(@PathVariable @Positive(message = "Id must be a positive number") Integer id) {
        return artistService.findOneById(id);
    }

    @PostMapping("/artists")
    public Artist createArtist(@Valid @RequestBody CreateArtistRequest createArtistRequest) {
        return artistService.createArtist(createArtistRequest);
    }

    @DeleteMapping("/artists/{id}")
    public void deleteArtist(@PathVariable @Positive(message = "Id must be a positive number") Integer id) {
        artistService.deleteArtist(id);
    }

    @PutMapping("/artists/{id}")
    public Artist updateArtist(@PathVariable @Positive(message = "Id must be a positive number") Integer id, @Valid @RequestBody UpdateArtistRequest updateArtistRequest) {
        return artistService.updateArtist(id, updateArtistRequest);
    }
}
