package com.oodmi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WinCombination {
    @JsonProperty("reward_multiplier")
    private int rewardMultiplier;
    private When when;
    private int count;
    private Group group;
    @JsonProperty("covered_areas")
    private List<List<String>> coveredAreas;
}
