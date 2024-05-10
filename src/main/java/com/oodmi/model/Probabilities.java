package com.oodmi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Probabilities {
    @JsonProperty("standard_symbols")
    private List<ProbabilitySymbol> standardSymbols;
    @JsonProperty("bonus_symbols")
    private List<ProbabilitySymbol> bonusSymbols;
}
