package com.iyzico.challenge.controller;

import com.iyzico.challenge.exceptions.NotFoundException;
import com.iyzico.challenge.flight.FlightService;
import com.iyzico.challenge.flight.dto.request.FlightCreate;
import com.iyzico.challenge.flight.dto.request.FlightUpdate;
import com.iyzico.challenge.flight.dto.response.FlightAndSeat;
import com.iyzico.challenge.flight.dto.response.FlightDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "api/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<FlightDTO> createFlight(@RequestBody FlightCreate request) {
        FlightDTO flightDTO = flightService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(flightDTO);
    }

    @GetMapping("all/flights")
    public ResponseEntity<List<FlightDTO>> getFlights() {
        List<FlightDTO> flightDTOs = flightService.getFlights();
        return ResponseEntity.ok().body(flightDTOs);
    }

    @GetMapping("seats")
    public ResponseEntity<FlightAndSeat> getFlightsWithSeat(@RequestParam Long id) {
        FlightAndSeat flightDTOs = flightService.getFlightsWithSeats(id);
        return ResponseEntity.ok().body(flightDTOs);
    }


    @PutMapping
    public ResponseEntity<FlightDTO> updateFlight(@RequestParam Long id, @RequestBody FlightUpdate request) throws NotFoundException {
        FlightDTO flightDTO = flightService.update(id, request);
        return ResponseEntity.ok().body(flightDTO);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteFlight(@RequestParam Long id) throws NotFoundException {
        flightService.delete(id);
        return ResponseEntity.ok().build();
    }
}