package com.iyzico.challenge.controller;

import com.iyzico.challenge.flight.FlightService;
import com.iyzico.challenge.flight.dto.request.FlightCreateRequest;
import com.iyzico.challenge.flight.dto.request.FlightUpdateRequest;
import com.iyzico.challenge.flight.dto.response.FlightAndSeatResponse;
import com.iyzico.challenge.flight.dto.response.FlightResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "api/v1/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<FlightResponse> createFlight(@RequestBody FlightCreateRequest request) {
        FlightResponse flightResponse = flightService.createFlight(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(flightResponse);
    }

    @GetMapping("all/flights")
    public ResponseEntity<List<FlightResponse>> getFlights() {
        List<FlightResponse> flightResponses = flightService.getFlights();
        return ResponseEntity.ok().body(flightResponses);
    }

    @GetMapping("seats")
    public ResponseEntity<FlightAndSeatResponse> getFlightsWithSeat(@RequestParam Long id) {
        FlightAndSeatResponse flightDTOs = flightService.getFlightsWithSeats(id);
        return ResponseEntity.ok().body(flightDTOs);
    }


    @PutMapping
    public ResponseEntity<FlightResponse> updateFlight(@RequestParam Long id, @RequestBody FlightUpdateRequest request){
        FlightResponse flightResponse = flightService.updateFlight(id, request);
        return ResponseEntity.ok().body(flightResponse);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteFlight(@RequestParam Long id){
        flightService.deleteFlight(id);
        return ResponseEntity.ok().build();
    }
}