package project.valery.SoloRandomizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.valery.SoloRandomizer.entities.Sequence;

public interface SequenceRepository extends JpaRepository<Sequence, Long> {
}
