package project.valery.SoloRandomizer.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.valery.SoloRandomizer.entities.Sequence;
import project.valery.SoloRandomizer.entities.User;
import project.valery.SoloRandomizer.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void getAllUsers() {
        // Arrange
        when(userService.getAllUsers()).thenReturn(Arrays.asList(new User(), new User()));

        // Act
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void getUserById() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        when(userService.getUserById(userId)).thenReturn(user);

        // Act
        ResponseEntity<User> response = userController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    // Add more test cases for createUser, updateUser, deleteUser, deleteAllUsers, addSequenceToUserByUsername,
    // addSequenceToUser, getRandomSequence, etc.

    // For example:
    @Test
    void createUser() {
        // Arrange
        User newUser = new User();
        when(userService.createUser(newUser)).thenReturn(newUser);

        // Act
        ResponseEntity<User> response = userController.createUser(newUser);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newUser, response.getBody());
    }

    @Test
    void updateUser() {
        // Arrange
        Long userId = 1L;
        User updatedUser = new User();
        User savedUser = new User();
        when(userService.updateUser(userId, updatedUser)).thenReturn(savedUser);

        // Act
        ResponseEntity<User> response = userController.updateUser(userId, updatedUser);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
    }

    @Test
    void deleteUser() {
        // Arrange
        Long userId = 1L;

        // Act
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    void deleteAllUsers() {
        // Act
        ResponseEntity<Void> response = userController.deleteAllUsers();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteAllUsers();
    }

    @Test
    void addSequenceToUserByUsername() {
        // Arrange
        String username = "testUser";
        int totalCounts = 10;
        String moveType = "testMove";
        Sequence newSequence = new Sequence();
        when(userService.addSequenceToUserByUsername(username, totalCounts, moveType)).thenReturn(newSequence);

        // Act
        ResponseEntity<Sequence> response = userController.addSequenceToUserByUsername(username, totalCounts, moveType);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newSequence, response.getBody());
    }

    @Test
    void addSequenceToUser() {
        // Arrange
        Long userId = 1L;
        int totalCounts = 10;
        String moveType = "testMove";
        Sequence newSequence = new Sequence();
        when(userService.addSequenceToUser(userId, totalCounts, moveType)).thenReturn(newSequence);

        // Act
        ResponseEntity<Sequence> response = userController.addSequenceToUser(userId, totalCounts, moveType);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newSequence, response.getBody());
    }

    @Test
    void getRandomSequence() {
        // Arrange
        int totalCounts = 10;
        String moveType = "testMove";
        Sequence randomSequence = new Sequence();
        when(userService.getRandomSequence(totalCounts, moveType)).thenReturn(randomSequence);

        // Act
        ResponseEntity<Sequence> response = userController.getRandomSequence(totalCounts, moveType);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(randomSequence, response.getBody());
    }

}
