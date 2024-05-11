package com.oodmi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record WinCombination(
        @JsonProperty("reward_multiplier")
        int rewardMultiplier,
        When when,
        Integer count,
        Group group,
        @JsonProperty("covered_areas")
        List<List<String>> coveredAreas) {
}
