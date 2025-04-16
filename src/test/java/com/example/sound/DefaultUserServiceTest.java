package com.example.sound;
import com.example.sound.pojo.entity.*;
import com.example.sound.repository.GenreRepository;
import com.example.sound.repository.UserRepository;
import com.example.sound.service.DefaultUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class DefaultUserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private DefaultUserService userService;

    @Test
    public void testRecommendSongsBasedOnGenre() {
        User user = new User();
        user.setId(1);
        user.setSongs(new HashSet<>());

        Genre genre1 = new Genre();
        genre1.setId(1);
        genre1.setSongs(new HashSet<>());

        Genre genre2 = new Genre();
        genre2.setId(2);
        genre2.setSongs(new HashSet<>());

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(genreRepository.findMostListenedGenresByUserId(1)).thenReturn(Arrays.asList(1, 2));
        when(genreRepository.findAllById(Arrays.asList(1, 2))).thenReturn(Arrays.asList(genre1, genre2));

        List<Song> recommendedSongs = userService.recommendSongsBasedOnGenre(1);

        assertNotNull(recommendedSongs, "Recommended songs should not be null");
    }
}
