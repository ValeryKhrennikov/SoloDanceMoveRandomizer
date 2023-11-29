package project.valery.SoloRandomizer.randomizer.names;

import org.springframework.stereotype.Component;

@Component
public enum BigNamesOfMove implements NamesOfMove {
    AROUND_THE_WORLD(8),
    APPLE_JACKS(2, 4, 6, 8);
    private final int[] counts;

    BigNamesOfMove(int... counts) {
        this.counts = counts;
    }

    @Override
    public int[] getCountsArray() {
        return counts;
    }
    @Override
    public String getType(){return "Big";}


}

