package com.iyzico.challenge.flight;

import com.iyzico.challenge.flight.dto.response.FlightAndSeatResponse;
import com.iyzico.challenge.flight.dto.response.FlightResponse;
import com.iyzico.challenge.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "Spring")
public interface FlightMapper {

    @Mapping(target = "id", source = "id")
    FlightResponse toFlightResponse(Flight entity);

    FlightAndSeatResponse toFlightAndSeatTest(Flight entity);

}