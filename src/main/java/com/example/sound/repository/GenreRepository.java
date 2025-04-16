package com.example.sound.repository;
import com.example.sound.pojo.entity.Genre;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {
    @Query("SELECT g FROM Genre g WHERE g.name IN :names")
    List<Genre> findAllByNameIn(@Param("names") List<String> names);

    @Modifying
    @Query(value = "DELETE FROM genre_song WHERE song_id = :songId", nativeQuery = true)
    void deleteGenreSongAssociationsBySongId(@Param("songId") Integer songId);

    @Modifying
    @Query(value = "DELETE FROM genre_album WHERE album_id = :albumId", nativeQuery = true)
    void deleteGenreAlbumAssociationsByAlbumId(@Param("albumId") Integer albumId);

    @Query("SELECT DISTINCT g.id FROM Genre g " +
            "JOIN g.songs s " +
            "JOIN s.users u " +
            "WHERE u.id = :userId " +
            "GROUP BY g.id " +
            "ORDER BY COUNT(s.id) DESC")
    List<Integer> findMostListenedGenresByUserId(@Param("userId") Integer userId);
}
