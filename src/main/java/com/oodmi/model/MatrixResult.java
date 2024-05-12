package com.oodmi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record MatrixResult(
        List<List<String>> matrix,
        Integer reward,
        @JsonProperty("applied_winning_combinations")
        Map<String, List<String>> appliedWinningCombinations,
        @JsonProperty("applied_bonus_symbol")
        String appliedBonusSymbol) {
}