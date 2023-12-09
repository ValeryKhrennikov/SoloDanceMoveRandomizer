package project.valery.SoloRandomizer.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sequence", schema = "public")
@Data
public class Sequence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(unique = true, nullable = false)
    private String savedSequence;
}
