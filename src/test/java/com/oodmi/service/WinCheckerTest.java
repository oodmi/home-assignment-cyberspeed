package com.oodmi.service;

import com.oodmi.model.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class WinCheckerTest {

    @Test
    void checkWinningCombinationsTest() {
        Configuration configuration = InputParser.parseConfig("config.json");
        List<List<String>> matrix = List.of(
                List.of("E", "E", "C"),
                List.of("D", "E", "D"),
                List.of("+1000", "E", "D")
        );

        Map<String, List<String>> winningCombinations = WinChecker.checkWinningCombinations(matrix, configuration);

        Assertions.assertEquals(Map.of(
                        "D", List.of("same_symbol_3_times"),
                        "E", List.of("same_symbol_4_times")),
                winningCombinations);
    }

    @Test
    void checkNoWinningCombinationsTest() {
        Configuration configuration = InputParser.parseConfig("config.json");
        List<List<String>> matrix = List.of(
                List.of("E", "E", "C"),
                List.of("D", "E", "D"),
                List.of("+1000", "E", "D")
        );

        Map<String, List<String>> winningCombinations = WinChecker.checkWinningCombinations(matrix, configuration);

        Assertions.assertEquals(Map.of(), winningCombinations);
    }
}
