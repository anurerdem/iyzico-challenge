package com.iyzico.challenge.flight.dto.request;

import com.iyzico.challenge.seat.dto.response.SeatDTO;
import com.iyzico.challenge.seat.entity.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FlightCreate {

    private String flightName;
    private String description;
    private List<Seat> seats;
    private double price;
    private String callSign;
    private String takeOff;
    private String land;



}
