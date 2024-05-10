package com.oodmi.enums;

import java.util.function.Function;

public enum BonusSymbolEnum {
    TEN_X(it -> it * 10),
    FIVE_X(it -> it * 5),
    PLUS_THOUSAND(it -> it + 1000),
    PLUS_FIVE_HUNDRED(it -> it + 500),
    MISS(it -> it);

    private final Function<Double, Double> function;

    BonusSymbolEnum(Function<Double, Double> function) {
        this.function = function;
    }

    public Double apply(Double value) {
        return this.function.apply(value);
    }
}
