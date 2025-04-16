package com.example.sound;
import com.example.sound.pojo.entity.Playlist;
import com.example.sound.pojo.entity.Song;
import com.example.sound.repository.PlaylistRepository;
import com.example.sound.repository.SongRepository;
import com.example.sound.service.DefaultPlaylistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DefaultPlaylistServiceTest {
    @Mock
    private SongRepository songRepository;

    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private DefaultPlaylistService playlistService;

    @Test
    public void testAddSongToPlaylist() {
        Playlist playlist = new Playlist();
        playlist.setSongs(new HashSet<>());

        Song song = new Song();
        song.setPlaylists(new HashSet<>());

        Integer playlistId = 1;
        Integer songId = 2;

        when(playlistRepository.findById(playlistId)).thenReturn(Optional.of(playlist));
        when(songRepository.findById(songId)).thenReturn(Optional.of(song));

        assertDoesNotThrow(() -> {
            playlistService.addSongToPlaylist(playlistId, songId);
        }, "Service method should not throw an exception.");

        assertNotNull(playlist.getSongs(), "Playlist songs should not be null");
        assertNotNull(song.getPlaylists(), "Song playlists should not be null");

        assertTrue(playlist.getSongs().contains(song), "Playlist should contain the song");
        assertTrue(song.getPlaylists().contains(playlist), "Song should be in the playlist");
    }

    @Test
    public void testRemoveSongFromPlaylist() {
        Playlist playlist = new Playlist();
        playlist.setSongs(new HashSet<>());

        Song song = new Song();
        song.setPlaylists(new HashSet<>());

        Integer playlistId = 1;
        Integer songId = 2;

        when(playlistRepository.findById(playlistId)).thenReturn(Optional.of(playlist));
        when(songRepository.findById(songId)).thenReturn(Optional.of(song));

        playlistService.removeSongFromPlaylist(playlistId, songId);

        assertNotNull(playlist.getSongs(), "Playlist songs should not be null");
        assertNotNull(song.getPlaylists(), "Song playlists should not be null");

        assertFalse(playlist.getSongs().contains(song), "Playlist should not contain the song");
        assertFalse(song.getPlaylists().contains(playlist), "Song should not be in the playlist");
    }
}