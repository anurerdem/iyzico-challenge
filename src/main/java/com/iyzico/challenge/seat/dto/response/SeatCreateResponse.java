package com.iyzico.challenge.seat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatCreateResponse {
    private String seatNumber;
    private BigDecimal seatPrice;
}
