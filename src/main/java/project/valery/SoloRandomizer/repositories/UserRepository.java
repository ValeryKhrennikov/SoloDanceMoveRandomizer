package project.valery.SoloRandomizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.valery.SoloRandomizer.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
