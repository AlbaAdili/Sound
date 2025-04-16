package com.example.sound.service;
import com.example.sound.pojo.dto.CreateGenreRequest;
import com.example.sound.pojo.dto.UpdateGenreRequest;
import com.example.sound.pojo.entity.Genre;
import com.example.sound.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DefaultGenreService implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return (List<Genre>) genreRepository.findAll();
    }

    @Override
    public Genre findOneById(Integer id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    public Genre createGenre(CreateGenreRequest createGenreRequest) {
        Genre genre = new Genre();

        genre.setName(createGenreRequest.getName());

        genreRepository.save(genre);

        return genre;
    }

    @Override
    public void deleteGenre(Integer id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + id));;

        if (genre != null) {
            genre.getAlbums().forEach(album -> album.getGenres().remove(genre));
            genre.getAlbums().clear();

            genre.getSongs().forEach(song -> song.getGenres().remove(genre));
            genre.getSongs().clear();

            genreRepository.deleteById(id);
        }
    }

    @Override
    public Genre updateGenre(Integer id, UpdateGenreRequest updateGenreRequest) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + id));;

        if(genre != null) {
            genre.setName(updateGenreRequest.getName());
            genreRepository.save(genre);
        }
        return genre;
    }
}
