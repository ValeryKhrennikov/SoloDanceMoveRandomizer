package project.valery.SoloRandomizer.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.valery.SoloRandomizer.entities.Sequence;
import project.valery.SoloRandomizer.services.SequenceService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class SequenceControllerTest {

    @Mock
    private SequenceService sequenceService;

    @InjectMocks
    private SequenceController sequenceController;

    @Test
    void getAllSequences() {
        // Arrange
        when(sequenceService.getAllSequences()).thenReturn(Arrays.asList(new Sequence(), new Sequence()));

        // Act
        ResponseEntity<List<Sequence>> response = sequenceController.getAllSequences();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void getSequenceById() {
        // Arrange
        Long sequenceId = 1L;
        Sequence sequence = new Sequence();
        when(sequenceService.getSequenceById(sequenceId)).thenReturn(sequence);

        // Act
        ResponseEntity<Sequence> response = sequenceController.getSequenceById(sequenceId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sequence, response.getBody());
    }

    @Test
    void getSequenceByIdNotFound() {
        // Arrange
        Long sequenceId = 1L;
        when(sequenceService.getSequenceById(sequenceId)).thenReturn(null);

        // Act
        ResponseEntity<Sequence> response = sequenceController.getSequenceById(sequenceId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteSequence() {
        // Arrange
        Long sequenceId = 1L;

        // Act
        ResponseEntity<Void> response = sequenceController.deleteSequence(sequenceId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(sequenceService, times(1)).deleteSequence(sequenceId);
    }

    @Test
    void deleteAllSequences() {
        // Act
        ResponseEntity<Void> response = sequenceController.deleteAllSequences();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(sequenceService, times(1)).deleteAllSequences();
    }
}
