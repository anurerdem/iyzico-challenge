package com.iyzico.challenge.flight.dto.request;

import com.iyzico.challenge.entity.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FlightCreateRequest {

    private String flightName;
    private String description;
    private List<Seat> seats;
    private double price;
    private String callSign;
    private String takeOff;
    private String land;



}
