package com.iyzico.challenge.flight.dto.response;


import com.iyzico.challenge.seat.dto.response.SeatDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightAndSeat extends FlightDTO {
    private List<SeatDTO> seats;
}