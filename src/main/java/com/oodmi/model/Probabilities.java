package com.oodmi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Probabilities(
        @JsonProperty("standard_symbols")
        List<ProbabilitySymbol> standardSymbols,
        @JsonProperty("bonus_symbols")
        ProbabilitySymbol bonusSymbols) {
}
