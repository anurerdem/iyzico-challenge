package com.iyzico.challenge.controller;

import com.iyzico.challenge.flight.dto.response.FlightAndSeat;
import com.iyzico.challenge.flight.dto.response.FlightDTO;
import com.iyzico.challenge.flight.entity.Flight;
import com.iyzico.challenge.seat.dto.response.SeatCreate;
import com.iyzico.challenge.seat.dto.response.SeatDTO;
import com.iyzico.challenge.seat.entity.Seat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface MessageMapper {

     FlightDTO toFlightDto(Flight entity);

     FlightAndSeat convertFlightToFlightAndSeatTest(Flight entity);

    SeatDTO toSeatDto(Seat entity);
    Seat toSeatEntity(SeatCreate dto);

    List<SeatDTO> convertSeatEntityListToSeatDtoList(List<Seat> entityList);

}
