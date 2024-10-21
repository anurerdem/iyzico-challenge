package com.iyzico.challenge.seat.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
public class SeatResponseResponse extends SeatCreateResponse {
    private Long id;
    private Boolean isSold;

    public SeatResponseResponse(Long id, String seatNumber, BigDecimal seatPrice) {
        super(seatNumber, seatPrice);
        this.id = id;
    }

}
