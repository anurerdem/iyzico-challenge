package com.iyzico.challenge.flight;


import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.enums.ErrorCode;
import com.iyzico.challenge.exceptions.FlightException;
import com.iyzico.challenge.flight.dto.request.FlightCreateRequest;
import com.iyzico.challenge.flight.dto.request.FlightUpdateRequest;
import com.iyzico.challenge.flight.dto.response.FlightAndSeatResponse;
import com.iyzico.challenge.flight.dto.response.FlightResponse;
import com.iyzico.challenge.flight.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper messageMapper;

    public FlightService(FlightRepository flightRepository, FlightMapper messageMapper) {
        this.flightRepository = flightRepository;
        this.messageMapper = messageMapper;
    }

    @Transactional
    public void deleteFlight(Long id) {
        Flight flight = this.getFlight(id);
        flight.setDeleteFlag(true);
        log.info("Flight information is deleted!");
        flightRepository.save(flight);
    }

    public List<FlightResponse> getFlights() {

        List<Flight> flights = flightRepository.findAll();
        return flights.stream().map(messageMapper::toFlightResponse).collect(Collectors.toList());
    }

    public FlightAndSeatResponse getFlightsWithSeats(Long id) {
        Flight flights = getFlight(id);
        log.info("Flight information was brought!");
        return messageMapper.toFlightAndSeatTest(flights);
    }

    @Transactional
    public FlightResponse createFlight(FlightCreateRequest request) {
        Flight flight = new Flight();
        flight.setFlightName(request.getFlightName());
        flight.setDescription(request.getDescription());
        flight.setTakeOff(request.getTakeOff());
        flight.setLand(request.getLand());

        Flight saved = flightRepository.saveAndFlush(flight);
        log.info("Flight saved successfully!");

        return messageMapper.toFlightResponse(saved);
    }

    @Transactional
    public FlightResponse updateFlight(Long id, FlightUpdateRequest request) {
        Flight flight = getFlight(id);
        flight.setFlightName(request.getFlightName());
        flight.setDescription(request.getDescription());

        flightRepository.save(flight);
        log.info("Flight updated successfully!");

        return messageMapper.toFlightResponse(flight);
    }

    @Transactional
    public Flight getFlight(Long flightId) {
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        if (flightOptional.isEmpty()) {
            log.error("No flight found for flight identifier: {}", flightId);
            throw new FlightException(ErrorCode.FLIGHT_NOT_FOUND);
        }
        return flightOptional.get();
    }


}