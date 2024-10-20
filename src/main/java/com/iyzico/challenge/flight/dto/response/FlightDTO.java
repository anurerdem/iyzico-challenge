package com.iyzico.challenge.flight.dto.response;

import com.iyzico.challenge.seat.dto.response.SeatDTO;
import com.iyzico.challenge.seat.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO implements Serializable {
    private Long id;
    private String flightName;
    private String callSign;
    private String description;
    private String takeOff;
    private String land;
}