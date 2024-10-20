package com.iyzico.challenge.controller;

import com.iyzico.challenge.exceptions.NotFoundException;
import com.iyzico.challenge.exceptions.SeatException;
import com.iyzico.challenge.seat.SeatService;
import com.iyzico.challenge.seat.dto.request.SeatCreateRequest;
import com.iyzico.challenge.seat.dto.request.SeatUpdateRequest;
import com.iyzico.challenge.seat.dto.response.SeatDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/seat", produces = MediaType.APPLICATION_JSON_VALUE)
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping
    public ResponseEntity<List<SeatDTO>> addSeat(@RequestBody SeatCreateRequest request) throws NotFoundException {
        List<SeatDTO> seatDTO = seatService.add(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(seatDTO);
    }

    @GetMapping
    public ResponseEntity<List<SeatDTO>> getAvailableSeats(@RequestParam Long id) throws NotFoundException {
        List<SeatDTO> seatDTOs = seatService.getAvailableSeats(id);
        return ResponseEntity.ok().body(seatDTOs);
    }

    @PutMapping
    public  ResponseEntity<List<SeatDTO>> updateSeats(@RequestBody SeatUpdateRequest request) throws NotFoundException, SeatException {
        List<SeatDTO> seatDTO = seatService.updateSeats(request);
        return ResponseEntity.ok().body(seatDTO);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteSeat(@RequestParam Long id) throws NotFoundException, SeatException {
        seatService.delete(id);
        return ResponseEntity.ok().build();
    }

    //SEAT SAT
}