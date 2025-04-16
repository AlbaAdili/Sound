package com.example.sound.service;
import com.example.sound.pojo.dto.*;
import com.example.sound.pojo.entity.Artist;
import java.util.List;

public interface ArtistService {
    List<Artist> findAll();
    Artist findOneById(Integer id);
    Artist createArtist(CreateArtistRequest createArtistRequest);
    void deleteArtist(Integer id);
    Artist updateArtist(Integer id, UpdateArtistRequest updateArtistRequest);
}
