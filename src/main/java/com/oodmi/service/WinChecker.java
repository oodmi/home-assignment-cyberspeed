package com.oodmi.service;

import com.oodmi.enums.When;
import com.oodmi.model.Configuration;
import com.oodmi.model.WinCombination;

import java.util.*;

import static com.oodmi.enums.Type.STANDARD;

public class WinChecker {

    public static Map<String, List<String>> checkWinningCombinations(List<List<String>> matrix, Configuration config) {
        Map<String, List<String>> appliedWinningCombinations = new HashMap<>();

        checkSameSymbolsCombinations(matrix, config, appliedWinningCombinations);
        checkLinearCombinations(matrix, config, appliedWinningCombinations);

        return appliedWinningCombinations;
    }

    private static void checkSameSymbolsCombinations(List<List<String>> matrix,
                                                     Configuration config,
                                                     Map<String, List<String>> appliedWinningCombinations) {
        Map<Integer, Map.Entry<String, WinCombination>> sameSymbolsCombinations = new HashMap<>();

        config.winCombinations().forEach((k, v) -> {
            if (v.when().equals(When.SAME_SYMBOLS)) {
                sameSymbolsCombinations.put(v.count(), Map.entry(k, v));
            }
        });

        List<String> allCells = matrix.stream().flatMap(Collection::stream).toList();

        config.symbols().forEach((k, v) -> {
            if (STANDARD.equals(v.type())) {
                int count = (int) allCells.stream().filter(it -> it.equals(k)).count();
                if (sameSymbolsCombinations.containsKey(count)) {
                    Map.Entry<String, WinCombination> winCombinationEntry = sameSymbolsCombinations.get(count);
                    ArrayList<String> combinations = new ArrayList<>();
                    combinations.add(winCombinationEntry.getKey());
                    appliedWinningCombinations.put(k, combinations);
                }
            }
        });
    }


    private static void checkLinearCombinations(List<List<String>> matrix,
                                                Configuration config,
                                                Map<String, List<String>> appliedWinningCombinations) {
        Map<String, WinCombination> linearCombinations = new HashMap<>();

        config.winCombinations().forEach((k, v) -> {
            if (v.when().equals(When.LINEAR_SYMBOLS)) {
                linearCombinations.put(k, v);
            }
        });

        linearCombinations.forEach((k, v) -> {

            List<List<String>> coveredAreas = v.coveredAreas();

            for (List<String> area : coveredAreas) {
                Set<String> set = new HashSet<>();
                for (String cell : area) {
                    String[] coordinates = cell.split(":");
                    int r = Integer.parseInt(coordinates[0]);
                    int c = Integer.parseInt(coordinates[1]);
                    set.add(matrix.get(r).get(c));
                }

                if (set.size() == 1) {
                    String symbol = set.iterator().next();
                    List<String> combinations = appliedWinningCombinations.getOrDefault(symbol, new ArrayList<>());
                    combinations.add(k);
                    appliedWinningCombinations.put(symbol, combinations);
                }
            }
        });
    }
}
