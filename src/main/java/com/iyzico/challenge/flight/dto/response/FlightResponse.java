package com.iyzico.challenge.flight.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class FlightResponse implements Serializable {
    private Long id;
    private String flightName;
    private String callSign;
    private String description;
    private String takeOff;
    private String land;
}