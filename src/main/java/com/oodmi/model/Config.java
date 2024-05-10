package com.oodmi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Config {
    private Integer columns;
    private Integer rows;
    private Map<String, Symbol> symbols;
    private Probabilities probabilities;
    @JsonProperty("win_combinations")
    private Map<String, WinCombination> winCombinations;
}
