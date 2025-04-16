package com.example.sound.service;
import com.example.sound.pojo.dto.CreatePlaylistRequest;
import com.example.sound.pojo.dto.UpdatePlaylistRequest;
import com.example.sound.pojo.entity.Playlist;
import com.example.sound.pojo.entity.Song;
import com.example.sound.pojo.entity.User;
import com.example.sound.repository.PlaylistRepository;
import com.example.sound.repository.SongRepository;
import com.example.sound.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Service
public class DefaultPlaylistService implements PlaylistService {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Playlist> findAll() {
        return (List<Playlist>) playlistRepository.findAll();
    }

    @Override
    public Playlist findOneById(Integer id) {
        return playlistRepository.findById(id).orElse(null);
    }

    @Override
    public Playlist createPlaylist(@Valid @RequestBody CreatePlaylistRequest createPlaylistRequest) {
        Playlist playlist = new Playlist();

        playlist.setName(createPlaylistRequest.getName());
        playlist.setDescription(createPlaylistRequest.getDescription());

        if (createPlaylistRequest.getUserId() != null) {
            User user = userRepository.findById(createPlaylistRequest.getUserId()).orElse(null);
            playlist.setUser(user);
        }

        playlistRepository.save(playlist);

        return playlist;
    }

    @Override
    public void deletePlaylist(@PathVariable @Positive(message = "Id must be a positive number") Integer id) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Playlist not found with id: " + id));;

        if (playlist != null) {
            playlist.getSongs().forEach(song -> song.getPlaylists().remove(playlist));
            playlist.getSongs().clear();

            playlistRepository.deleteById(id);
        }
    }

    @Override
    public Playlist updatePlaylist(@PathVariable @Positive(message = "Id must be a positive number") Integer id, @Valid @RequestBody UpdatePlaylistRequest updatePlaylistRequest) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Playlist not found with id: " + id));;

        if (playlist != null) {
            if (updatePlaylistRequest.getName() != null) {
                playlist.setName(updatePlaylistRequest.getName());
            }

            if (updatePlaylistRequest.getDescription() != null) {
                playlist.setDescription(updatePlaylistRequest.getDescription());
            }
            playlistRepository.save(playlist);
        }

        return playlist;
    }

    @Override
    public Song addSongToPlaylist(Integer playlistId, Integer songId) {
        if (playlistId == null || songId == null) {
            throw new IllegalStateException("playlistId and songId must not be null");
        }

        Playlist playlist = findOneById(playlistId);
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new EntityNotFoundException("Song not found with id: " + songId));;

        if (playlist != null && song != null) {
            playlist.getSongs().add(song);
            song.getPlaylists().add(playlist);

            playlistRepository.save(playlist);
            songRepository.save(song);

            return song;
        } else {
            throw new IllegalStateException("Invalid playlistId or songId provided.");
        }
    }

    @Override
    public void removeSongFromPlaylist(Integer playlistId, Integer songId) {
        if (playlistId == null || songId == null) {
            throw new IllegalStateException("playlistId and songId must not be null");
        }

        Playlist playlist = findOneById(playlistId);
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new EntityNotFoundException("Song not found with id: " + songId));;

        if (playlist != null && song != null) {
            playlist.getSongs().remove(song);
            song.getPlaylists().remove(playlist);

            playlistRepository.save(playlist);
            songRepository.save(song);
        } else {
            throw new IllegalStateException("Invalid playlistId or songId provided.");
        }
    }
}
