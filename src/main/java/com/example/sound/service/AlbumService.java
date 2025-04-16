package com.example.sound.service;
import com.example.sound.pojo.dto.CreateAlbumRequest;
import com.example.sound.pojo.dto.UpdateAlbumRequest;
import com.example.sound.pojo.entity.Album;
import java.util.List;

public interface AlbumService {
    List<Album> findAll();
    Album findOneById(Integer id);
    Album createAlbum(Integer artistId, CreateAlbumRequest createAlbumRequest);
    void deleteAlbum(Integer artistId, Integer albumId);
    Album updateAlbum(Integer artistId, Integer albumId, UpdateAlbumRequest updateAlbumRequest);
}
