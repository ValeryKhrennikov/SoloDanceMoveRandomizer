package project.valery.SoloRandomizer.entities;

import jakarta.persistence.*;
import lombok.Data;
import project.valery.SoloRandomizer.randomizer.Movement;

import java.util.List;

@Entity
@Table(name = "sequence", schema = "public")
@Data
public class Sequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "sequence", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movement> movements;


}