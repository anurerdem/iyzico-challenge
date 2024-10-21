package com.iyzico.challenge.service;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.flight.FlightService;
import com.iyzico.challenge.seat.SeatMapper;
import com.iyzico.challenge.seat.SeatService;
import com.iyzico.challenge.seat.dto.request.SeatCreateRequest;
import com.iyzico.challenge.seat.dto.request.SeatUpdateRequest;
import com.iyzico.challenge.seat.dto.response.SeatResponseResponse;
import com.iyzico.challenge.seat.repository.SeatRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SeatServiceTest {

    @InjectMocks
    private SeatService seatService;

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private FlightService flightService;

    @Mock
    private SeatMapper seatMapper;

    @Test
    public void addSeat_When_Id_Is_Null() {
        SeatCreateRequest request = new SeatCreateRequest();
        request.setSeats(new ArrayList<>());
        when(flightService.getFlight(null)).thenReturn(null);
        when(seatRepository.saveAllAndFlush(any())).thenReturn(List.of());
        List<SeatResponseResponse> actual = seatService.addSeat(request);
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    public void updateSeats_When_Request_Is_Empty() {

        when(seatRepository.saveAll(any())).thenReturn(List.of());
        List<SeatResponseResponse> actual = seatService.updateSeats(new SeatUpdateRequest(new HashMap<>()));
        Assertions.assertTrue(actual.isEmpty());

    }

    @Test
    public void getAvailableSeats_WhenSendExistId_ThenReturnAvailableSeats() {
        Flight flight = new Flight();
        flight.setId(1L);
        List<Seat> availableSeats = new ArrayList<>();
        Seat seat = new Seat();
        seat.setSeatPrice(BigDecimal.TEN);
        seat.setSeatNumber("10");
        seat.setId(1L);
        availableSeats.add(seat);
        SeatResponseResponse dto = new SeatResponseResponse();
        dto.setId(1L);
        when(flightService.getFlight(1L)).thenReturn(flight);
        when(seatRepository.findSeatsByFlightAndIsSoldFalse(flight)).thenReturn(availableSeats);
        List<SeatResponseResponse> out = new ArrayList<>();
        out.add(dto);
        when(seatMapper.convertSeatEntityListToSeatDtoList(availableSeats)).thenReturn(out);
        List<SeatResponseResponse> actual = seatService.getAvailableSeats(1L);

        List<SeatResponseResponse> expected = new ArrayList<>();
        expected.add(dto);
        assertEquals(expected.get(0).getId(), actual.get(0).getId());

    }

    @Test
    public void getSeat_WhenSendExistId_ThenReturnSeat() {

        when(seatRepository.findById(1L)).thenReturn(Optional.of(new Seat()));
        Seat actual = seatService.getSeat(1L);
        Assertions.assertNotNull(actual);

    }

}
