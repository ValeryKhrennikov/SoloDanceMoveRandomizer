package project.valery.SoloRandomizer.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.valery.SoloRandomizer.entities.Sequence;
import project.valery.SoloRandomizer.entities.User;
import project.valery.SoloRandomizer.exceptions.UserNotFoundException;
import project.valery.SoloRandomizer.randomizer.Randomizer;
import project.valery.SoloRandomizer.repositories.SequenceRepository;
import project.valery.SoloRandomizer.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final Randomizer randomizer; // Внедрение зависимости Randomizer

    @Autowired
    public UserService(UserRepository userRepository, SequenceRepository sequenceRepository, Randomizer randomizer) {
        this.userRepository = userRepository;
        this.randomizer = randomizer;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        existingUser.setUsername(updatedUser.getUsername());
        // Обновить другие поля при необходимости

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public Sequence addSequenceToUser(Long userId, int totalCounts, String moveType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        String randomMovesAsString = randomizer.getRandomMovesAsString(totalCounts, moveType);

        Sequence newSequence = new Sequence();
        newSequence.setSavedSequence(randomMovesAsString);

        user.getSavedSequences().add(newSequence);
//        newSequence.setUser(user); // Установка владельца последовательности

        userRepository.save(user);

        return newSequence;
    }

    public Sequence getRandomSequence(int totalCounts, String moveType) {
        String randomMovesAsString = randomizer.getRandomMovesAsString(totalCounts, moveType);

        Sequence randomSequence = new Sequence();
        randomSequence.setSavedSequence(randomMovesAsString);

        return randomSequence;
    }
}
