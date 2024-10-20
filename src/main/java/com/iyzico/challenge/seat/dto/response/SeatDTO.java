package com.iyzico.challenge.seat.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
public class SeatDTO extends SeatCreate {
    private Long id;
    private Boolean isSold;

    public SeatDTO(Long id, String seatNumber, BigDecimal seatPrice) {
        super(seatNumber, seatPrice);
        this.id = id;
    }

}
