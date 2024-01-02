package project.valery.SoloRandomizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.valery.SoloRandomizer.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
