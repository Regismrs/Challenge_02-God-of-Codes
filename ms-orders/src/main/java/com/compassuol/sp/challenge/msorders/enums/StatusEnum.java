package com.compassuol.sp.challenge.msorders.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StatusEnum {
    @JsonProperty("CONFIRMED")
    CONFIRMED,
    @JsonProperty("SENT")
    SENT,
    @JsonProperty("CANCELED")
    CANCELED
}
