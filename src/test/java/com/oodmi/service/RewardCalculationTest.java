package com.oodmi.service;

import com.oodmi.model.Configuration;
import com.oodmi.model.InputParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class RewardCalculationTest {

    @Test
    void calculateRewardTest() {
        Configuration configuration = InputParser.parseConfig("src/test/resources/config.json");

        Double reward = RewardCalculation.calculateReward(Map.of(
                "D", List.of("same_symbol_3_times"),
                "E", List.of("same_symbol_4_times")), new InputParams(configuration, 100));

        Assertions.assertEquals(950, reward);
    }

    @Test
    void applyBonusTest() {
        Configuration configuration = InputParser.parseConfig("src/test/resources/config.json");

        Map<String, List<String>> winningCombinations = Map.of(
                "D", List.of("same_symbol_3_times"),
                "E", List.of("same_symbol_4_times"));

        List<List<String>> matrix = List.of(
                List.of("E", "E", "C"),
                List.of("D", "E", "D"),
                List.of("+1000", "E", "D")
        );

        Double calculateReward = RewardCalculation.calculateReward(winningCombinations, new InputParams(configuration, 100));
        Map.Entry<Double, String> doubleStringEntry = RewardCalculation.applyBonus(winningCombinations, matrix, new InputParams(configuration, 100), calculateReward);

        Assertions.assertEquals(Map.entry(1950.0, "+1000"), doubleStringEntry);
    }
}