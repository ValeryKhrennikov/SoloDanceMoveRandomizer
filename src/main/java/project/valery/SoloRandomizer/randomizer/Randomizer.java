package project.valery.SoloRandomizer.randomizer;

import project.valery.SoloRandomizer.randomizer.names.BigMoveName;
import project.valery.SoloRandomizer.randomizer.names.MoveName;
import project.valery.SoloRandomizer.randomizer.names.SmallMoveName;

import java.util.*;

public class Randomizer {
    private final List<MoveName> allMoves;
    private final List<MoveName> smallMoves;
    private final List<MoveName> bigMoves;

    private final Map<MoveName, Integer> usageCounts;

    public Randomizer() {
        // Initialize lists and add all possible moves
        allMoves = new ArrayList<>();
        allMoves.addAll(List.of(SmallMoveName.values()));
        allMoves.addAll(List.of(BigMoveName.values()));

        smallMoves = new ArrayList<>(List.of(SmallMoveName.values()));
        bigMoves = new ArrayList<>(List.of(BigMoveName.values()));

        // Initialize usage counts map
        usageCounts = new HashMap<>();
        for (MoveName move : allMoves) {
            usageCounts.put(move, 0);
        }
    }

    public List<Movement> getRandomMoves(int totalCounts, String moveType) {
        List<Movement> randomMoves = new ArrayList<>();
        Random random = new Random();
        int countsSoFar = 0;

        List<MoveName> selectedMoves;
        switch (moveType) {
            case "ALL":
                selectedMoves = allMoves;
                break;
            case "SMALL":
                selectedMoves = smallMoves;
                break;
            case "BIG":
                selectedMoves = bigMoves;
                break;
            default:
                throw new IllegalArgumentException("Invalid Move Type");
        }

        while (countsSoFar < totalCounts) {
            MoveName randomName = getNextRandomName(random, selectedMoves);

            int numberOfCounts = getRandomNumberOfCounts(randomName.getCountsArray(), random);

            // Check if adding the current movement exceeds the total counts
            if (countsSoFar + numberOfCounts <= totalCounts) {
                boolean isLegChanged = random.nextBoolean();
                String randomType = randomName.getType();
                Movement movement = new Movement(randomName, isLegChanged, randomType, numberOfCounts);
                randomMoves.add(movement);
                countsSoFar += numberOfCounts;

                // Increase the usage count for the chosen name
                usageCounts.put(randomName, usageCounts.get(randomName) + 1);
            }
        }

        return randomMoves;
    }

    public int getRandomNumberOfCounts(int[] array, Random random) {
        // Проверка на пустой массив
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }
        // Получаем случайный индекс из массива
        int randomIndex = random.nextInt(array.length);

        // Возвращаем случайное число из массива по случайному индексу
        return array[randomIndex];
    }

    private MoveName getNextRandomName(Random random, List<MoveName> availableMoves) {
        List<MoveName> eligibleMoves = new ArrayList<>();

        for (MoveName move : availableMoves) {
            int usageCount = usageCounts.get(move);
            int reducedProbability = Math.max(1, 1 << usageCount);
            if (random.nextDouble() < 1.0 / reducedProbability) {
                eligibleMoves.add(move);
            }
        }

        if (eligibleMoves.isEmpty()) {
            // If all names have been used, choose randomly from available moves
            return availableMoves.get(random.nextInt(availableMoves.size()));
        } else {
            // Choose randomly from eligible names
            return eligibleMoves.get(random.nextInt(eligibleMoves.size()));
        }
    }

    public static void main(String[] args) {
        Randomizer randomizer = new Randomizer();
        List<Movement> randomMoves = randomizer.getRandomMoves(12,"BIG");

        for (Movement movement : randomMoves) {
            System.out.println(movement);
        }
    }


}
