package com.iyzico.challenge.flight.dto.response;


import com.iyzico.challenge.seat.dto.response.SeatResponseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightAndSeatResponse extends FlightResponse {
    private List<SeatResponseResponse> seats;
}