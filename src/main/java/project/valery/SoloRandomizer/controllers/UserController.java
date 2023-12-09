package project.valery.SoloRandomizer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.valery.SoloRandomizer.entities.Sequence;
import project.valery.SoloRandomizer.entities.User;
import project.valery.SoloRandomizer.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User savedUser = userService.updateUser(id, updatedUser);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete-all-users")
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{userId}/add-sequence")
    public ResponseEntity<Sequence> addSequenceToUser(
            @PathVariable Long userId,
            @RequestParam int totalCounts,
            @RequestParam String moveType
    ) {
        Sequence newSequence = userService.addSequenceToUser(userId, totalCounts, moveType);
        return new ResponseEntity<>(newSequence, HttpStatus.CREATED);
    }

    @GetMapping("/random-sequence")
    public ResponseEntity<Sequence> getRandomSequence(
            @RequestParam int totalCounts,
            @RequestParam String moveType
    ) {
        Sequence randomSequence = userService.getRandomSequence(totalCounts, moveType);
        return new ResponseEntity<>(randomSequence, HttpStatus.OK);
    }
}
