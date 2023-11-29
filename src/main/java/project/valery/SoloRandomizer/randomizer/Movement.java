package project.valery.SoloRandomizer.randomizer;

import org.springframework.stereotype.Component;
import project.valery.SoloRandomizer.randomizer.names.MoveName;

//@Data
@Component
public class Movement {
    private MoveName name;
    private boolean isLegChanged;
    private String typeOfMove; // "Big" или "Small"
    private int numberOfCounts;

    public Movement(MoveName name, boolean isLegChanged, String typeOfMove, int numberOfCounts) {
        this.name = name;
        this.isLegChanged = isLegChanged;
        this.typeOfMove = typeOfMove;
        this.numberOfCounts = numberOfCounts;

    }

    @Override
    public java.lang.String toString() {
        return name +
                " on " + numberOfCounts;

    }
}
