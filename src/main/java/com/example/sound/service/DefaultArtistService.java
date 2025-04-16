package com.example.sound.service;
import com.example.sound.pojo.dto.*;
import com.example.sound.pojo.entity.Artist;
import com.example.sound.repository.ArtistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DefaultArtistService implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public List<Artist> findAll() {
        return (List<Artist>) artistRepository.findAll();
    }

    @Override
    public Artist findOneById(Integer id) {
        return artistRepository.findById(id).orElse(null);
    }

    @Override
    public Artist createArtist(CreateArtistRequest createArtistRequest) {
        Artist artist = new Artist();

        artist.setName(createArtistRequest.getName());
        artist.setSurname(createArtistRequest.getSurname());
        artist.setBirthDate(createArtistRequest.getBirthDate());
        artist.setCountry(createArtistRequest.getCountry());
        artist.setCity(createArtistRequest.getCity());
        artist.setMonthlyListeners(0);
        artist.setBiography(createArtistRequest.getBiography());

        artistRepository.save(artist);

        return artist;
    }

    @Override
    public void deleteArtist(Integer id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artist not found with id: " + id));

        if(artist != null) {
            artistRepository.deleteById(id);
        }
    }

    @Override
    public Artist updateArtist(Integer id, UpdateArtistRequest updateArtistRequest) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artist not found with id: " + id));

        if (artist != null) {
            if (updateArtistRequest.getName() != null) {
                artist.setName(updateArtistRequest.getName());
            }

            if (updateArtistRequest.getSurname() != null) {
                artist.setSurname(updateArtistRequest.getSurname());
            }

            if (updateArtistRequest.getBirthDate() != null) {
                artist.setBirthDate(updateArtistRequest.getBirthDate());
            }

            if (updateArtistRequest.getCountry() != null) {
                artist.setCountry(updateArtistRequest.getCountry());
            }

            if (updateArtistRequest.getCity() != null) {
                artist.setCity(updateArtistRequest.getCity());
            }

            if (updateArtistRequest.getBiography() != null) {
                artist.setBiography(updateArtistRequest.getBiography());
            }

            artistRepository.save(artist);
        }
        return artist;
    }
}
