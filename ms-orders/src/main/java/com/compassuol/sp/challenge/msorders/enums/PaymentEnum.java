package com.compassuol.sp.challenge.msorders.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PaymentEnum {
    @JsonProperty("CREDIT_CARD")
    CREDIT_CARD,
    @JsonProperty("BANK_TRANSFER")
    BANK_TRANSFER,
    @JsonProperty("CRYPTOCURRENCY")
    CRYPTOCURRENCY,
    @JsonProperty("GIFT_CARD")
    GIFT_CARD,
    @JsonProperty("PIX")
    PIX,
    @JsonProperty("OTHER")
    OTHER
}

