package com.example.sound.service;
import com.example.sound.pojo.dto.CreateUserRequest;
import com.example.sound.pojo.dto.UpdateUserRequest;
import com.example.sound.pojo.entity.Artist;
import com.example.sound.pojo.entity.Genre;
import com.example.sound.pojo.entity.Song;
import com.example.sound.pojo.entity.User;
import com.example.sound.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DefaultUserService implements UserService {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findOneById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User user = new User();

        user.setName(createUserRequest.getName());
        user.setSurname(createUserRequest.getSurname());
        user.setBirthDate(createUserRequest.getBirthDate());
        user.setCountry(createUserRequest.getCountry());
        user.setCity(createUserRequest.getCity());

        userRepository.save(user);

        return user;
    }

    @Override
    public void deleteUser(@PathVariable @Positive(message = "Id must be a positive number") Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));;

        if (user != null) {
            user.getPlaylists().forEach(playlist -> playlistRepository.deleteById(playlist.getId()));

            user.getSongs().forEach(song -> song.getUsers().remove(user));
            user.getSongs().clear();

            userRepository.deleteById(id);
        }
    }

    @Override
    public User updateUser(@PathVariable @Positive(message = "Id must be a positive number") Integer id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));;

        if (user != null) {
            if (updateUserRequest.getName() != null) {
                user.setName(updateUserRequest.getName());
            }

            if (updateUserRequest.getSurname() != null) {
                user.setSurname(updateUserRequest.getSurname());
            }

            if (updateUserRequest.getBirthDate() != null) {
                user.setBirthDate(updateUserRequest.getBirthDate());
            }

            if (updateUserRequest.getCountry() != null) {
                user.setCountry(updateUserRequest.getCountry());
            }

            if (updateUserRequest.getCity() != null) {
                user.setCity(updateUserRequest.getCity());
            }

            userRepository.save(user);
        }

        return user;
    }

    @Override
    @Transactional
    public void playSong(Integer songId, Integer userId) {
        if (songId == null || userId == null) {
            throw new IllegalStateException("songId and userId must not be null");
        }

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new EntityNotFoundException("Song not found with id: " + songId));

        song.setPlays(song.getPlays() + 1);
        songRepository.save(song);

        User user = findOneById(userId);
        if (user == null) {
            throw new IllegalStateException("User not found with id: " + userId);
        }

        Artist artist = song.getAlbum().getArtists().iterator().next();

        // Check if the user has already played a song by the artist this month
        boolean alreadyPlayedThisMonth = artist.getMonthlyListeners() > 0;

        if (!alreadyPlayedThisMonth) {
            // Increment monthly listeners for the artist
            artist.setMonthlyListeners(artist.getMonthlyListeners() + 1);
            artistRepository.save(artist);
        }

        user.getSongs().add(song);
        userRepository.save(user);

        song.getUsers().add(user);
        songRepository.save(song);

        recommendSongsBasedOnGenre(userId);
    }

    @Scheduled(cron = "0 0 0 1 * ?") // Run at midnight on the first day of each month
    @Transactional
    public void resetMonthlyListeners() {
        artistRepository.findAll().forEach(artist -> artist.setMonthlyListeners(0));
    }

    private Date getStartOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    @Override
    @Transactional
    public List<Song> recommendSongsBasedOnGenre(Integer userId) {
        User user = findOneById(userId);
        List<Song> recommendedSongs = new ArrayList<>();

        if (user != null) {
            List<Integer> mostListenedGenreIds = genreRepository.findMostListenedGenresByUserId(userId);
            List<Genre> mostListenedGenres = (List<Genre>) genreRepository.findAllById(mostListenedGenreIds);

            for (Genre genre : mostListenedGenres) {
                List<Song> songsInGenre = genre.getSongs().stream()
                        .filter(s -> !user.getSongs().contains(s)) // Exclude songs the user has already listened to
                        .toList();

                recommendedSongs.addAll(songsInGenre);
            }
        }
        return recommendedSongs;
    }
}
