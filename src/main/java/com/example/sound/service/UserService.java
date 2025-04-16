package com.example.sound.service;
import com.example.sound.pojo.dto.CreateUserRequest;
import com.example.sound.pojo.dto.UpdateUserRequest;
import com.example.sound.pojo.entity.Song;
import com.example.sound.pojo.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findOneById(Integer id);
    User createUser(CreateUserRequest createUserRequest);
    void deleteUser(Integer id);
    User updateUser(Integer id, UpdateUserRequest updateUserRequest);
    void playSong(Integer songId, Integer userId);
    List<Song> recommendSongsBasedOnGenre(Integer userId);
}
