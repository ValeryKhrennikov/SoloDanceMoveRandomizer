package project.valery.SoloRandomizer.randomizer.names;

public enum BigMoveName implements MoveName {
    AROUND_THE_WORLD(4,8),
    APPLE_JACKS(4, 6, 8),
    BOOGIE_DROP4(4,8),
    BOX_STEP(4,8),
    BREAK_FULL_TOBA(8),
    BREAK_HALF(4,8),
    BUTTROLL(4,8);
    private final int[] counts;

    BigMoveName(int... counts) {
        this.counts = counts;
    }

    @Override
    public int[] getCountsArray() {
        return counts;
    }
    @Override
    public String getType(){return "Big";}


}

