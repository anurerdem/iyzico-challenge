package com.iyzico.challenge.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SeatStatus {

    AVAILABLE("Available"),
    SOLD("Sold");

    private String value;

    SeatStatus(final String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
