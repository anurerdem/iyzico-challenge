package com.iyzico.challenge.controller;

import com.iyzico.challenge.seat.SeatService;
import com.iyzico.challenge.seat.dto.request.SeatCreateRequest;
import com.iyzico.challenge.seat.dto.request.SeatUpdateRequest;
import com.iyzico.challenge.seat.dto.response.SeatResponseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/seat", produces = MediaType.APPLICATION_JSON_VALUE)
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping
    public ResponseEntity<List<SeatResponseResponse>> addSeat(@RequestBody SeatCreateRequest request) {
        List<SeatResponseResponse> seatResponse = seatService.addSeat(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(seatResponse);
    }

    @GetMapping
    public ResponseEntity<List<SeatResponseResponse>> getAvailableSeats(@RequestParam Long id) {
        List<SeatResponseResponse> seatResponses = seatService.getAvailableSeats(id);
        return ResponseEntity.ok().body(seatResponses);
    }

    @PutMapping
    public ResponseEntity<List<SeatResponseResponse>> updateSeats(@RequestBody SeatUpdateRequest request) {
        List<SeatResponseResponse> seatResponse = seatService.updateSeats(request);
        return ResponseEntity.ok().body(seatResponse);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteSeat(@RequestParam Long id) {
        seatService.deleteSeat(id);
        return ResponseEntity.ok().build();
    }

}