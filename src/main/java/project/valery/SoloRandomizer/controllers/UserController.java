package project.valery.SoloRandomizer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.valery.SoloRandomizer.entities.Sequence;
import project.valery.SoloRandomizer.entities.User;
import project.valery.SoloRandomizer.exceptions.UserNotFoundException;
import project.valery.SoloRandomizer.randomizer.Movement;
import project.valery.SoloRandomizer.randomizer.Randomizer;
import project.valery.SoloRandomizer.repositories.UserRepository;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;
    private final Randomizer randomizer; // Внедрение зависимости Randomizer

    @Autowired
    public UserController(UserRepository userRepository, Randomizer randomizer) {
        this.userRepository = userRepository;
        this.randomizer = randomizer;
    }

    // Получить всех пользователей
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Получить пользователя по ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Создать нового пользователя
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userRepository.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // Обновить информацию о пользователе по ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        existingUser.setUsername(updatedUser.getUsername());
        // Обновить другие поля при необходимости

        User savedUser = userRepository.save(existingUser);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    // Удалить пользователя по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Добавление новой последовательности движений для пользователя
    @PostMapping("/{userId}/add-sequence")
    public ResponseEntity<Sequence> addSequenceToUser(
            @PathVariable Long userId,
            @RequestParam int totalCounts,
            @RequestParam String moveType
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        List<Movement> randomMoves = randomizer.getRandomMoves(totalCounts, moveType);

        Sequence newSequence = new Sequence();
        newSequence.setUser(user);
        // Установите движения для новой последовательности
        newSequence.setMovements(randomMoves);

        user.getSavedSequences().add(newSequence);
        userRepository.save(user);

        return new ResponseEntity<>(newSequence, HttpStatus.CREATED);
    }

    // Получение новой последовательности движений не закрепленной за пользователем
    @GetMapping("/random-sequence")
    public ResponseEntity<Sequence> getRandomSequence(
            @RequestParam int totalCounts,
            @RequestParam String moveType
    ) {
        List<Movement> randomMoves = randomizer.getRandomMoves(totalCounts, moveType);

        Sequence randomSequence = new Sequence();
        // Установите движения для новой последовательности
        randomSequence.setMovements(randomMoves);

        return new ResponseEntity<>(randomSequence, HttpStatus.OK);
    }
}
