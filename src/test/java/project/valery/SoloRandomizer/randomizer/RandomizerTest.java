package project.valery.SoloRandomizer.randomizer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.valery.SoloRandomizer.randomizer.names.BigMoveName;
import project.valery.SoloRandomizer.randomizer.names.MoveName;
import project.valery.SoloRandomizer.randomizer.names.SmallMoveName;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class RandomizerTest {

    private Randomizer randomizer;
    private MoveName mockSmallMove;
    private MoveName mockBigMove;

    @BeforeEach
    public void setUp() {
        randomizer = new Randomizer();
        mockSmallMove = mock(SmallMoveName.class);
        mockBigMove = mock(BigMoveName.class);
    }

    @Test
    public void testGetRandomMovesAsString() {
        // Мокируем методы getCountsArray и getType для тестирования
        when(mockSmallMove.getCountsArray()).thenReturn(new int[]{2, 4, 6});
        when(mockSmallMove.getType()).thenReturn("Small");
        when(mockBigMove.getCountsArray()).thenReturn(new int[]{4, 8});
        when(mockBigMove.getType()).thenReturn("Big");

        String result = randomizer.getRandomMovesAsString(10, "ALL");

        assertNotNull(result);
        assertTrue(result.contains("totalCounts:"));
    }

    @Test
    public void testGetRandomNumberOfCounts() {
        int[] countsArray = {2, 4, 6};
        Random random = new Random();
        int result = randomizer.getRandomNumberOfCounts(countsArray, random);

        assertTrue(result >= 2 && result <= 6);
    }

    @Test
    public void testGetNextRandomName() {
        List<MoveName> availableMoves = new ArrayList<>();
        availableMoves.addAll(List.of(SmallMoveName.values()));
        availableMoves.addAll(List.of(BigMoveName.values()));;



        MoveName result = randomizer.getNextRandomName(new Random(), availableMoves);

        assertNotNull(result);
        assertTrue(availableMoves.contains(result));
    }

    // Другие тесты по необходимости
}

