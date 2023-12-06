package project.valery.SoloRandomizer.randomizer;

import org.springframework.stereotype.Component;
import project.valery.SoloRandomizer.randomizer.names.MoveName;

//@Data
@Component
public class Movement {
    private final MoveName name;
    private final boolean isLegChanged;
    private final String typeOfMove; // "Big" или "Small"
    private final int numberOfCounts;

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
