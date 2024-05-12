package com.oodmi;

import com.oodmi.model.Configuration;
import com.oodmi.model.InputParams;
import com.oodmi.model.MatrixResult;
import com.oodmi.service.InputParser;
import com.oodmi.service.RewardCalculation;
import com.oodmi.service.WinChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class ScratchGameTest {

    @Test
    void fullFlowTest() {
        Configuration configuration = InputParser.parseConfig("src/test/resources/config.json");

        List<List<String>> matrix = List.of(
                List.of("E", "E", "C"),
                List.of("D", "E", "D"),
                List.of("+1000", "E", "D")
        );

        InputParams inputParams = new InputParams(configuration, 100);

        Map<String, List<String>> winningCombinations = WinChecker.checkWinningCombinations(matrix, inputParams.config());
        Double reward = RewardCalculation.calculateReward(winningCombinations, inputParams);
        Map.Entry<Double, String> rewardWithBonus = RewardCalculation.applyBonus(winningCombinations, matrix, inputParams, reward);

        MatrixResult matrixResult = new MatrixResult(matrix,
                rewardWithBonus.getKey().intValue(),
                winningCombinations,
                rewardWithBonus.getValue());

        MatrixResult expected = new MatrixResult(matrix, 2400, Map.of(
                "D", List.of("same_symbol_3_times"),
                "E", List.of("same_symbol_4_times", "same_symbols_vertically")),
                "+1000");

        Assertions.assertEquals(expected, matrixResult);
    }

    @Test
    void fullFlowFromExampleTest() {
        Configuration configuration = InputParser.parseConfig("src/test/resources/config.json");

        List<List<String>> matrix = List.of(
                List.of("A", "A", "B"),
                List.of("A", "+1000", "B"),
                List.of("A", "A", "B")
        );

        InputParams inputParams = new InputParams(configuration, 100);

        Map<String, List<String>> winningCombinations = WinChecker.checkWinningCombinations(matrix, inputParams.config());
        Double reward = RewardCalculation.calculateReward(winningCombinations, inputParams);
        Map.Entry<Double, String> rewardWithBonus = RewardCalculation.applyBonus(winningCombinations, matrix, inputParams, reward);

        MatrixResult matrixResult = new MatrixResult(matrix,
                rewardWithBonus.getKey().intValue(),
                winningCombinations,
                rewardWithBonus.getValue());

        MatrixResult expected = new MatrixResult(matrix, 26000, Map.of(
                "A", List.of("same_symbol_5_times", "same_symbols_vertically"),
                "B", List.of("same_symbol_3_times", "same_symbols_vertically")),
                "+1000");

        Assertions.assertEquals(expected, matrixResult);
    }

    @Test
    void fullLostFlowFromExampleTest() {
        Configuration configuration = InputParser.parseConfig("src/test/resources/config.json");

        List<List<String>> matrix = List.of(
                List.of("A", "B", "C"),
                List.of("E", "B", "5x"),
                List.of("F", "D", "C")
        );

        InputParams inputParams = new InputParams(configuration, 100);

        Map<String, List<String>> winningCombinations = WinChecker.checkWinningCombinations(matrix, inputParams.config());
        Double reward = RewardCalculation.calculateReward(winningCombinations, inputParams);
        Map.Entry<Double, String> rewardWithBonus = RewardCalculation.applyBonus(winningCombinations, matrix, inputParams, reward);

        MatrixResult matrixResult = new MatrixResult(matrix,
                rewardWithBonus.getKey().intValue(),
                winningCombinations,
                rewardWithBonus.getValue());

        MatrixResult expected = new MatrixResult(matrix, 0, Map.of(),
                "");

        Assertions.assertEquals(expected, matrixResult);
    }
}