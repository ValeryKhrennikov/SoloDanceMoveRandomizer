package project.valery.SoloRandomizer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.valery.SoloRandomizer.entities.Sequence;
import project.valery.SoloRandomizer.exceptions.SequenceNotFoundException;
import project.valery.SoloRandomizer.repositories.SequenceRepository;
import project.valery.SoloRandomizer.randomizer.Randomizer;
import project.valery.SoloRandomizer.randomizer.Movement;
import project.valery.SoloRandomizer.entities.User;
import project.valery.SoloRandomizer.exceptions.UserNotFoundException;
import project.valery.SoloRandomizer.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/sequences")
public class SequenceController {
    private final SequenceRepository sequenceRepository;


    @Autowired
    public SequenceController(SequenceRepository sequenceRepository, UserRepository userRepository, Randomizer randomizer) {
        this.sequenceRepository = sequenceRepository;
    }

    // Получить все последовательности
    @GetMapping
    public ResponseEntity<List<Sequence>> getAllSequences() {
        List<Sequence> sequences = sequenceRepository.findAll();
        return new ResponseEntity<>(sequences, HttpStatus.OK);
    }

    // Получить последовательность по ID
    @GetMapping("/{id}")
    public ResponseEntity<Sequence> getSequenceById(@PathVariable Long id) {
        Sequence sequence = sequenceRepository.findById(id)
                .orElseThrow(() -> new SequenceNotFoundException("Sequence not found with id: " + id));
        return new ResponseEntity<>(sequence, HttpStatus.OK);
    }

    // Удалить последовательность по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSequence(@PathVariable Long id) {
        sequenceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
