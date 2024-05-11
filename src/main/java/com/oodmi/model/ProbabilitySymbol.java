package com.oodmi.model;

import java.util.Map;

public record ProbabilitySymbol(
        Integer column,
        Integer row,
        Map<String, Integer> symbols) {
}
