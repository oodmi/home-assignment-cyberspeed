package com.oodmi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Symbol(
        @JsonProperty("reward_multiplier")
        Double rewardMultiplier,
        Integer extra,
        Type type,
        Impact impact) {
}
