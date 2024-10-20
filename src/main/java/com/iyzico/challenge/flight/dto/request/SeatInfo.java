package com.iyzico.challenge.flight.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SeatInfo {
    private String seatNumber;
    private BigDecimal seatPrice;
}
