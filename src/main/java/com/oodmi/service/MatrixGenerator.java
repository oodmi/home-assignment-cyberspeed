package com.oodmi.service;

import com.oodmi.model.Configuration;
import com.oodmi.model.ProbabilitySymbol;

import java.util.*;
import java.util.stream.Collectors;

public class MatrixGenerator {

    public static List<List<String>> generateMatrix(Configuration config) {
        List<List<String>> matrix = new ArrayList<>();

        Map<String, Map<String, Integer>> probabilities = config.probabilities()
                .standardSymbols()
                .stream()
                .collect(Collectors.toMap(it -> it.row() + ":" + it.column(), ProbabilitySymbol::symbols));

        int numRows = config.rows();
        int numColumns = config.columns();
        Random random = new Random();

        boolean bonus = true;

        for (int i = 0; i < numRows; i++) {
            List<String> row = new ArrayList<>();

            for (int j = 0; j < numColumns; j++) {
                List<String> allSymbols = new ArrayList<>();
                List<String> bonusSymbols = new ArrayList<>();

                Map<String, Integer> symbols = probabilities.getOrDefault(i + ":" + j, new HashMap<>());
                symbols.forEach((k, v) -> {
                    for (int l = 0; l < v; l++) {
                        allSymbols.add(k);
                    }
                });

                config.probabilities().bonusSymbols().symbols().forEach((k, v) -> {
                    for (int l = 0; l < v; l++) {
                        bonusSymbols.add(k);
                    }
                });

                if (random.nextInt(numColumns * numRows) < numColumns * numRows / 4 && bonus) {
                    int randomProbability = random.nextInt(bonusSymbols.size());
                    row.add(bonusSymbols.get(randomProbability));
                    bonus = false;
                } else {
                    int randomProbability = random.nextInt(allSymbols.size());
                    row.add(allSymbols.get(randomProbability));
                }
            }

            matrix.add(row);
        }

        return matrix;
    }
}
