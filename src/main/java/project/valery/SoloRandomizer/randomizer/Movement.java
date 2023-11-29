package project.valery.SoloRandomizer.randomizer;

import org.springframework.stereotype.Component;
import project.valery.SoloRandomizer.randomizer.names.NamesOfMove;

//@Data
@Component
public class Movement {
    private NamesOfMove name;
    private boolean isLegChanged;
    private String typeOfMove; // "Big" или "Small"
    private int numberOfCounts;

    public Movement(NamesOfMove name, boolean isLegChanged, String typeOfMove, int numberOfCounts) {
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
