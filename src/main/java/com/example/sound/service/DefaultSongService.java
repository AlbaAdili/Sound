package com.example.sound.service;
import com.example.sound.pojo.dto.CreateSongRequest;
import com.example.sound.pojo.dto.UpdateSongRequest;
import com.example.sound.pojo.entity.Album;
import com.example.sound.pojo.entity.Genre;
import com.example.sound.pojo.entity.Song;
import com.example.sound.repository.AlbumRepository;
import com.example.sound.repository.GenreRepository;
import com.example.sound.repository.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DefaultSongService implements SongService {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public List<Song> findAll() {
        return (List<Song>) songRepository.findAll();
    }

    @Override
    public Song findOneById(Integer id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Song createSong(CreateSongRequest createSongRequest) {
        Song song = new Song();

        song.setName(createSongRequest.getName());
        song.setDuration(createSongRequest.getDuration());
        song.setPlays(0);

        if (createSongRequest.getAlbumId() != null) {
            Album album = albumRepository.findById(createSongRequest.getAlbumId())
                    .orElseThrow(() -> new EntityNotFoundException("Album not found with id: " + createSongRequest.getAlbumId()));;
            song.setAlbum(album);
        }

        List<Genre> genres = genreRepository.findAllByNameIn(createSongRequest.getGenreNames());
        song.setGenres(new HashSet<>(genres));

        for (Genre genre : genres) {
            genre.addSong(song);
            genreRepository.save(genre);
        }

        songRepository.save(song);

        return song;
    }

    @Override
    @Transactional
    public void deleteSong(Integer songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new EntityNotFoundException("Song not found with id: " + songId));;

        if (song != null) {
            Set<Genre> genres = new HashSet<>(song.getGenres());

            for (Genre genre : genres) {
                genre.removeSong(song);
                genreRepository.save(genre);
            }

            Album album = song.getAlbum();
            if (album != null) {
                album.getSongs().remove(song);
                albumRepository.save(album);
            }

            songRepository.deleteById(songId);
        }
    }

    @Override
    @Transactional
    public Song updateSong(Integer songId, UpdateSongRequest updateSongRequest) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new EntityNotFoundException("Song not found with id: " + songId));;

        if (song != null) {
            if (updateSongRequest.getName() != null) {
                song.setName(updateSongRequest.getName());
            }

            genreRepository.deleteGenreSongAssociationsBySongId(songId);

            List<Genre> updatedGenres = genreRepository.findAllByNameIn(updateSongRequest.getGenreNames());

            song.getGenres().clear();

            for (Genre updatedGenre : updatedGenres) {
                song.addGenre(updatedGenre);
            }

            songRepository.save(song);

            return song;
        }
        return null;
    }
}
