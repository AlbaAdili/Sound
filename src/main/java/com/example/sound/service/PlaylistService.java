package com.example.sound.service;
import com.example.sound.pojo.dto.CreatePlaylistRequest;
import com.example.sound.pojo.dto.UpdatePlaylistRequest;
import com.example.sound.pojo.entity.Playlist;
import com.example.sound.pojo.entity.Song;
import java.util.List;

public interface PlaylistService {
    List<Playlist> findAll();
    Playlist findOneById(Integer id);
    Playlist createPlaylist(CreatePlaylistRequest createPlaylistRequest);
    void deletePlaylist(Integer id);
    Playlist updatePlaylist(Integer id, UpdatePlaylistRequest updatePlaylistRequest);
    Song addSongToPlaylist(Integer playlistId, Integer songId);
    void removeSongFromPlaylist(Integer playlistId, Integer songId);
}
