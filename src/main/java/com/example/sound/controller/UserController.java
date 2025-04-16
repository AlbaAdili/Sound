package com.example.sound.controller;
import com.example.sound.pojo.dto.CreateUserRequest;
import com.example.sound.pojo.dto.UpdateUserRequest;
import com.example.sound.pojo.entity.Song;
import com.example.sound.pojo.entity.User;
import com.example.sound.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findOneById(@PathVariable @Positive(message = "Id must be a positive number") Integer id) {
        return userService.findOneById(id);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable @Positive(message = "Id must be a positive number") Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable @Positive(message = "Id must be a positive number") Integer id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(id, updateUserRequest);
    }

    @PostMapping("/users/{userId}/play-song/{songId}")
    public void playSong(@PathVariable @Positive(message = "Id must be a positive number") Integer songId, @PathVariable @Positive(message = "Id must be a positive number") Integer userId) {
        userService.playSong(songId, userId);
    }

    @GetMapping("/users/{userId}/recommendations")
    public List<Song> getRecommendations(@PathVariable @Positive(message = "Id must be a positive number") Integer userId) {
        return userService.recommendSongsBasedOnGenre(userId);
    }
}
