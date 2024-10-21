package com.iyzico.challenge.service;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.flight.FlightMapper;
import com.iyzico.challenge.flight.FlightService;
import com.iyzico.challenge.flight.dto.request.FlightCreateRequest;
import com.iyzico.challenge.flight.dto.request.FlightUpdateRequest;
import com.iyzico.challenge.flight.dto.response.FlightResponse;
import com.iyzico.challenge.flight.repository.FlightRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {
    @InjectMocks
    private FlightService flightService;
    @Mock
    private FlightRepository flightRepository;
    @Mock
    private FlightMapper messageMapper;


    @Test
    public void getFlights_When_Is() {
        when(flightRepository.findAll()).thenReturn(List.of());
        List<FlightResponse> actual = flightService.getFlights();
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    public void getFlightsWithSeats_When_Id_Is_Empty() {
        when(flightRepository.findById(null)).thenReturn(Optional.of(new Flight()));
        FlightResponse actual = flightService.getFlightsWithSeats(null);
        Assertions.assertNull(actual);
    }

    @Test
    public void createFlight_When_Request_Is_Empty() {
        FlightCreateRequest request = new FlightCreateRequest();
        when(messageMapper.toFlightResponse(null)).thenReturn(null);
        FlightResponse actual = flightService.createFlight(request);
        Assertions.assertNull(actual);
    }

    @Test
    public void updateFlight_WhenSendExistIdAndRequest_ThenReturnFlightResponse() {

        FlightUpdateRequest request = new FlightUpdateRequest();
        request.setFlightName("Test");
        request.setDescription("TEST");
        Flight flight = new Flight();
        flight.setFlightName(request.getFlightName());
        flight.setDescription(request.getDescription());
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        FlightResponse out = new FlightResponse();
        out.setFlightName("Test");
        when(messageMapper.toFlightResponse(flight)).thenReturn(out);
        FlightResponse actual = flightService.updateFlight(1l, request);
        FlightResponse expected = new FlightResponse();
        expected.setFlightName("Test");
        assertEquals(expected.getFlightName(), actual.getFlightName());

    }


    @Test
    public void getFlight_WhenSendExistId_ThenReturnFlight() {

        when(flightRepository.findById(1L)).thenReturn(Optional.of(new Flight()));
        Flight actual = flightService.getFlight(1L);
        Assertions.assertNotNull(actual);

    }


}
