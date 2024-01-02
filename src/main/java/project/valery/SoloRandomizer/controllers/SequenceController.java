package project.valery.SoloRandomizer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.valery.SoloRandomizer.entities.Sequence;
import project.valery.SoloRandomizer.services.SequenceService;

import java.util.List;

@RestController
@RequestMapping("/api/sequences")
public class SequenceController {
    private final SequenceService sequenceService;

    @Autowired
    public SequenceController(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    @GetMapping
    public ResponseEntity<List<Sequence>> getAllSequences() {
        List<Sequence> sequences = sequenceService.getAllSequences();
        return new ResponseEntity<>(sequences, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sequence> getSequenceById(@PathVariable Long id) {
        Sequence sequence = sequenceService.getSequenceById(id);
        if (sequence != null) {
            return new ResponseEntity<>(sequence, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSequence(@PathVariable Long id) {
        sequenceService.deleteSequence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/delete-all-sequences")
    public ResponseEntity<Void> deleteAllSequences() {
        sequenceService.deleteAllSequences();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
