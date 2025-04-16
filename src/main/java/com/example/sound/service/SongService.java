package com.example.sound.service;
import com.example.sound.pojo.dto.CreateSongRequest;
import com.example.sound.pojo.dto.UpdateSongRequest;
import com.example.sound.pojo.entity.Song;
import java.util.List;

public interface SongService {
    List<Song> findAll();
    Song findOneById(Integer id);
    Song createSong(CreateSongRequest createSongRequest);
    void deleteSong(Integer songId);
    Song updateSong(Integer songId, UpdateSongRequest updateSongRequest);
}
