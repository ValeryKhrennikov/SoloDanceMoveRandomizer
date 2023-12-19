package project.valery.SoloRandomizer.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.valery.SoloRandomizer.entities.Sequence;
import project.valery.SoloRandomizer.exceptions.SequenceNotFoundException;
import project.valery.SoloRandomizer.repositories.SequenceRepository;

import java.util.List;

@Service
public class SequenceService {
    private final SequenceRepository sequenceRepository;

    @Autowired
    public SequenceService(SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    public List<Sequence> getAllSequences() {
        return sequenceRepository.findAll();
    }

    public Sequence getSequenceById(Long id) {
        return sequenceRepository.findById(id)
                .orElseThrow(() -> new SequenceNotFoundException("Sequence not found with id: " + id));
    }

    public void deleteSequence(Long id) {
        sequenceRepository.deleteById(id);
    }
    public void deleteAllSequences() {
        sequenceRepository.deleteAll();
    }
}
