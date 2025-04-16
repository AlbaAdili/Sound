package com.example.sound.controller;
import com.example.sound.pojo.dto.CreatePlaylistRequest;
import com.example.sound.pojo.dto.UpdatePlaylistRequest;
import com.example.sound.pojo.entity.Playlist;
import com.example.sound.pojo.entity.Song;
import com.example.sound.service.PlaylistService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/playlists")
    public List<Playlist> findAll() {
        return playlistService.findAll();
    }

    @GetMapping("/playlists/{id}")
    public Playlist findOneById(@PathVariable @Positive(message = "Id must be a positive number") Integer id) {
        return playlistService.findOneById(id);
    }

    @PostMapping("/playlists")
    public Playlist createPlaylist(@Valid @RequestBody CreatePlaylistRequest createPlaylistRequest) {
        return playlistService.createPlaylist(createPlaylistRequest);
    }

    @DeleteMapping("/playlists/{id}")
    public void deletePlaylist(@PathVariable @Positive(message = "Id must be a positive number") Integer id) {
        playlistService.deletePlaylist(id);
    }

    @PutMapping("/playlists/{id}")
    public Playlist updatePlaylist(@PathVariable @Positive(message = "Id must be a positive number") Integer id, @Valid @RequestBody UpdatePlaylistRequest updatePlaylistRequest) {
        return playlistService.updatePlaylist(id, updatePlaylistRequest);
    }

    @PostMapping("/playlists/{playlistId}/add-song/{songId}")
    public Song addSongToPlaylist(@PathVariable @Positive(message = "Id must be a positive number") Integer playlistId, @PathVariable @Positive(message = "Id must be a positive number") Integer songId) {
        return playlistService.addSongToPlaylist(playlistId, songId);
    }

    @DeleteMapping("/playlists/{playlistId}/remove-song/{songId}")
    public void removeSongFromPlaylist(@PathVariable @Positive(message = "Id must be a positive number") Integer playlistId, @PathVariable @Positive(message = "Id must be a positive number") Integer songId) {
        playlistService.removeSongFromPlaylist(playlistId, songId);
    }
}
