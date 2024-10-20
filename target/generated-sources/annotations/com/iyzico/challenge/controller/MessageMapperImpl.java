package com.iyzico.challenge.controller;

import com.iyzico.challenge.flight.dto.response.FlightAndSeat;
import com.iyzico.challenge.flight.dto.response.FlightDTO;
import com.iyzico.challenge.flight.entity.Flight;
import com.iyzico.challenge.seat.dto.response.SeatCreate;
import com.iyzico.challenge.seat.dto.response.SeatDTO;
import com.iyzico.challenge.seat.entity.Seat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-20T19:59:32+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.25 (Amazon.com Inc.)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public FlightDTO toFlightDto(Flight entity) {
        if ( entity == null ) {
            return null;
        }

        FlightDTO flightDTO = new FlightDTO();

        flightDTO.setId( entity.getId() );
        flightDTO.setFlightName( entity.getFlightName() );
        flightDTO.setCallSign( entity.getCallSign() );
        flightDTO.setDescription( entity.getDescription() );
        flightDTO.setTakeOff( entity.getTakeOff() );
        flightDTO.setLand( entity.getLand() );

        return flightDTO;
    }

    @Override
    public FlightAndSeat convertFlightToFlightAndSeatTest(Flight entity) {
        if ( entity == null ) {
            return null;
        }

        FlightAndSeat flightAndSeat = new FlightAndSeat();

        flightAndSeat.setId( entity.getId() );
        flightAndSeat.setFlightName( entity.getFlightName() );
        flightAndSeat.setCallSign( entity.getCallSign() );
        flightAndSeat.setDescription( entity.getDescription() );
        flightAndSeat.setTakeOff( entity.getTakeOff() );
        flightAndSeat.setLand( entity.getLand() );
        flightAndSeat.setSeats( convertSeatEntityListToSeatDtoList( entity.getSeats() ) );

        return flightAndSeat;
    }

    @Override
    public SeatDTO toSeatDto(Seat entity) {
        if ( entity == null ) {
            return null;
        }

        SeatDTO seatDTO = new SeatDTO();

        seatDTO.setSeatNumber( entity.getSeatNumber() );
        seatDTO.setSeatPrice( entity.getSeatPrice() );
        seatDTO.setId( entity.getId() );
        seatDTO.setIsSold( entity.getIsSold() );

        return seatDTO;
    }

    @Override
    public Seat toSeatEntity(SeatCreate dto) {
        if ( dto == null ) {
            return null;
        }

        Seat seat = new Seat();

        seat.setSeatNumber( dto.getSeatNumber() );
        seat.setSeatPrice( dto.getSeatPrice() );

        return seat;
    }

    @Override
    public List<SeatDTO> convertSeatEntityListToSeatDtoList(List<Seat> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SeatDTO> list = new ArrayList<SeatDTO>( entityList.size() );
        for ( Seat seat : entityList ) {
            list.add( toSeatDto( seat ) );
        }

        return list;
    }
}
