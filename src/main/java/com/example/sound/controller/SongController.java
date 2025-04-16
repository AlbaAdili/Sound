package com.example.sound.controller;
import com.example.sound.pojo.dto.CreateSongRequest;
import com.example.sound.pojo.dto.UpdateSongRequest;
import com.example.sound.pojo.entity.Song;
import com.example.sound.service.SongService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/songs")
    public List<Song> findAll() {
        return songService.findAll();
    }

    @GetMapping("/songs/{id}")
    public Song findOneById(@PathVariable @Positive(message = "Id must be a positive number") Integer id) {
        return songService.findOneById(id);
    }

    @PostMapping("/artists/songs")
    public Song createSong(@Valid @RequestBody CreateSongRequest createSongRequest) {
        return songService.createSong(createSongRequest);
    }

    @DeleteMapping("/artists/songs/{songId}")
    public void deleteSong(@PathVariable @Positive(message = "Id must be a positive number") Integer songId) {
        songService.deleteSong(songId);
    }

    @PutMapping("/artists/songs/{songId}")
    public Song updateSong(@PathVariable @Positive(message = "Id must be a positive number") Integer songId, @Valid @RequestBody UpdateSongRequest updateSongRequest) {
        return songService.updateSong(songId, updateSongRequest);
    }
}
