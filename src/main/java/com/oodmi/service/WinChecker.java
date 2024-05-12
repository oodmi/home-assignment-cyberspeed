package com.oodmi.service;

import com.oodmi.enums.Group;
import com.oodmi.model.Configuration;
import com.oodmi.model.WinCombination;

import java.util.*;

import static com.oodmi.enums.Type.standard;

public class WinChecker {

    public static Map<String, List<String>> checkWinningCombinations(List<List<String>> matrix, Configuration config) {
        Map<String, List<String>> appliedWinningCombinations = new HashMap<>();

        Map<Integer, Map.Entry<String, WinCombination>> sameSymbolsCombinations = new HashMap<>();

        config.winCombinations().forEach((k, v) -> {
            if (v.group().equals(Group.SAME_SYMBOLS)) {
                sameSymbolsCombinations.put(v.count(), Map.entry(k, v));
            }
        });

        List<String> allCells = matrix.stream().flatMap(Collection::stream).toList();

        config.symbols().forEach((k, v) -> {
            if (standard.equals(v.type())) {
                int count = (int) allCells.stream().filter(it -> it.equals(k)).count();
                if (sameSymbolsCombinations.containsKey(count)) {
                    Map.Entry<String, WinCombination> winCombinationEntry = sameSymbolsCombinations.get(count);
                    ArrayList<String> combinations = new ArrayList<>();
                    combinations.add(winCombinationEntry.getKey());
                    appliedWinningCombinations.put(k, combinations);
                }
            }
        });

        return appliedWinningCombinations;
    }
}
