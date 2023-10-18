package com.compassuol.sp.challenge.msfeedback.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ScaleEnum {

    @JsonProperty("VERY_DISSATISFIED")
    VERY_DISSATISFIED,
    @JsonProperty("DISSATISFIED")
    DISSATISFIED,
    @JsonProperty("NEUTRAL")
    NEUTRAL,
    @JsonProperty("SATISFIED")
    SATISFIED,
    @JsonProperty("VERY_SATISFIED")
    VERY_SATISFIED


}
