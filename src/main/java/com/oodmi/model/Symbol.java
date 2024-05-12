package com.oodmi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oodmi.enums.Impact;
import com.oodmi.enums.Type;

public record Symbol(
        @JsonProperty("reward_multiplier")
        Double rewardMultiplier,
        Integer extra,
        Type type,
        Impact impact) {
}
