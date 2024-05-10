package com.oodmi.enums;

public enum SymbolEnum {
    A(30),
    B(25),
    C(10),
    D(5),
    E(3),
    F(1.5);

    private final double rewardMultiplier;

    SymbolEnum(double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }
}
