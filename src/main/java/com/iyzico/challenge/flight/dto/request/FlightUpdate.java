package com.iyzico.challenge.flight.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlightUpdate {
    private String flightName;
    private String description;
}
