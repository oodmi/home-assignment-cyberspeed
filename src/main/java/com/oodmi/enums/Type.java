package com.oodmi.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Type {
    @JsonProperty("standard")
    STANDARD,
    @JsonProperty("bonus")
    BONUS
}
