package com.example.sound.service;
import com.example.sound.pojo.dto.CreateAlbumRequest;
import com.example.sound.pojo.dto.UpdateAlbumRequest;
import com.example.sound.pojo.entity.Album;
import com.example.sound.pojo.entity.Artist;
import com.example.sound.pojo.entity.Genre;
import com.example.sound.pojo.entity.Song;
import com.example.sound.repository.AlbumRepository;
import com.example.sound.repository.ArtistRepository;
import com.example.sound.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;

@Service
public class DefaultAlbumService implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Album> findAll() {
        return (List<Album>) albumRepository.findAll();
    }

    @Override
    public Album findOneById(Integer id) {
        return albumRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Album createAlbum(Integer artistId, CreateAlbumRequest createAlbumRequest) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException("Artist not found with id: " + artistId));

        if (artist != null) {
            Album album = new Album();
            album.setTitle(createAlbumRequest.getTitle());
            album.setReleaseDate(createAlbumRequest.getReleaseDate());

            List<Genre> genres = genreRepository.findAllByNameIn(createAlbumRequest.getGenreNames());

            album.setGenres(new HashSet<>(genres));

            artist.addAlbum(album);

            album = albumRepository.save(album);

            for (Genre genre : genres) {
                genre.addAlbum(album);
                genreRepository.save(genre);
            }

            return album;
        } else {
            throw new EntityNotFoundException("Artist not found with id: " + artistId);
        }
    }

    @Override
    @Transactional
    public void deleteAlbum(Integer artistId, Integer albumId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException("Artist not found with id: " + artistId));

        if (artist != null) {
            Album album = albumRepository.findById(albumId)
                    .orElseThrow(() -> new EntityNotFoundException("Album not found with id: " + albumId));

            if (album != null && artist.getAlbums().contains(album)) {
                artist.removeAlbum(album);
                artistRepository.save(artist);

                for (Song song : new HashSet<>(album.getSongs())) {
                    artistRepository.deleteById(song.getId());
                }

                for (Genre genre : new HashSet<>(album.getGenres())) {
                    genre.removeAlbum(album);
                    genreRepository.save(genre);
                }

                albumRepository.deleteById(albumId);
            }
        }
    }

    @Override
    @Transactional
    public Album updateAlbum(Integer artistId, Integer albumId, UpdateAlbumRequest updateAlbumRequest) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new EntityNotFoundException("Artist not found with id: " + artistId));

        if (artist != null) {
            Album album = albumRepository.findById(albumId)
                    .orElseThrow(() -> new EntityNotFoundException("Album not found with id: " + albumId));;

            if (album != null && artist.getAlbums().contains(album)) {
                if (updateAlbumRequest.getTitle() != null) {
                    album.setTitle(updateAlbumRequest.getTitle());
                }

                if (updateAlbumRequest.getGenreNames() != null && !updateAlbumRequest.getGenreNames().isEmpty()) {
                    genreRepository.deleteGenreAlbumAssociationsByAlbumId(albumId);

                    List<Genre> updatedGenres = genreRepository.findAllByNameIn(updateAlbumRequest.getGenreNames());

                    album.getGenres().clear();

                    for (Genre updatedGenre : updatedGenres) {
                        album.addGenre(updatedGenre);
                    }
                }

                albumRepository.save(album);

                return album;
            }
        }
        return null;
    }
}
