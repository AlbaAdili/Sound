package com.example.sound.controller;
import com.example.sound.pojo.dto.CreateAlbumRequest;
import com.example.sound.pojo.dto.UpdateAlbumRequest;
import com.example.sound.pojo.entity.Album;
import com.example.sound.service.AlbumService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @GetMapping("/albums")
    public List<Album> findAll() {
        return albumService.findAll();
    }

    @GetMapping("/albums/{id}")
    public Album findOneById(@PathVariable @Positive(message = "Id must be a positive number") Integer id) {
        return albumService.findOneById(id);
    }

    @PostMapping("/artists/{artistId}/albums")
    public Album createAlbum(@PathVariable @Positive(message = "Id must be a positive number") Integer artistId, @Valid @RequestBody CreateAlbumRequest createAlbumRequest) {
        return albumService.createAlbum(artistId, createAlbumRequest);
    }

    @DeleteMapping("/artists/{artistId}/albums/{albumId}")
    public void deleteAlbum(@PathVariable @Positive(message = "Id must be a positive number") Integer artistId, @PathVariable @Positive(message = "Id must be a positive number") Integer albumId) {
        albumService.deleteAlbum(artistId, albumId);
    }

    @PutMapping("/artists/{artistId}/albums/{albumId}")
    public Album updateAlbum(@PathVariable @Positive(message = "Id must be a positive number") Integer artistId, @PathVariable @Positive(message = "Id must be a positive number") Integer albumId, @Valid @RequestBody UpdateAlbumRequest updateAlbumRequest) {
        return albumService.updateAlbum(artistId, albumId, updateAlbumRequest);
    }
}
