package project.valery.SoloRandomizer.randomizer;

import project.valery.SoloRandomizer.randomizer.names.BigNamesOfMove;
import project.valery.SoloRandomizer.randomizer.names.NamesOfMove;
import project.valery.SoloRandomizer.randomizer.names.SmallNamesOfMove;

import java.util.*;

public class Randomizer {
    private final List<NamesOfMove> allMoves;
    private final Map<NamesOfMove, Integer> usageCounts;

    public Randomizer() {
        // Здесь инициализируйте список allMoves со всеми возможными движениями
        allMoves = new ArrayList<>();
        allMoves.addAll(List.of(BigNamesOfMove.values()));
        allMoves.addAll(List.of(SmallNamesOfMove.values()));

        // Инициализируем счетчики использования имен
        usageCounts = new HashMap<>();
        for (NamesOfMove move : allMoves) {
            usageCounts.put(move, 0);
        }
    }

    public List<Movement> getRandomMoves(int totalCounts) {
        List<Movement> randomMoves = new ArrayList<>();
        Random random = new Random();
        int countsSoFar = 0;

        while (countsSoFar < totalCounts) {
            NamesOfMove randomName = getNextRandomName(random);
            boolean isLegChanged = random.nextBoolean();
            String randomType = randomName.getType();
            int numberOfCounts = getRandomNumberOfCounts(randomName.getCountsArray(), random);

            // Check if adding the current movement exceeds the total counts
            if (countsSoFar + numberOfCounts <= totalCounts) {
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

    private NamesOfMove getNextRandomName(Random random) {
        List<NamesOfMove> availableMoves = new ArrayList<>();

        for (NamesOfMove move : allMoves) {
            int usageCount = usageCounts.get(move);
            int reducedProbability = Math.max(1, 1 << usageCount);
            if (random.nextDouble() < 1.0 / reducedProbability) {
                availableMoves.add(move);
            }
        }

        if (availableMoves.isEmpty()) {
            // Если все имена были использованы, выбираем случайное из всех
            return allMoves.get(random.nextInt(allMoves.size()));
        } else {
            // Выбираем случайное из доступных имен
            return availableMoves.get(random.nextInt(availableMoves.size()));
        }
    }

    public static void main(java.lang.String[] args) {
        Randomizer randomizer = new Randomizer();
        List<Movement> randomMoves = randomizer.getRandomMoves(10);

        for (Movement movement : randomMoves) {
            System.out.println(movement);
        }
    }
}
