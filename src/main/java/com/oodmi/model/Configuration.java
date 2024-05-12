package com.oodmi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record Configuration(
        Integer columns,
        Integer rows,
        Map<String, Symbol> symbols,
        Probability probabilities,
        @JsonProperty("win_combinations")
        Map<String, WinCombination> winCombinations) {
}
