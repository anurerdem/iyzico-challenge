package com.iyzico.challenge.flight;


import com.iyzico.challenge.controller.MessageMapper;
import com.iyzico.challenge.exceptions.NotFoundException;
import com.iyzico.challenge.flight.dto.request.FlightCreate;
import com.iyzico.challenge.flight.dto.request.FlightUpdate;
import com.iyzico.challenge.flight.dto.response.FlightAndSeat;
import com.iyzico.challenge.flight.dto.response.FlightDTO;
import com.iyzico.challenge.flight.entity.Flight;
import com.iyzico.challenge.flight.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FlightService {
    private final FlightRepository flightRepository;
    private final MessageMapper messageMapper;

    public FlightService(FlightRepository flightRepository, MessageMapper messageMapper) {
        this.flightRepository = flightRepository;

        this.messageMapper = messageMapper;
    }

    public void delete(Long id) throws NotFoundException {
        Flight flight = this.getFlight(id);
        flight.setDeleteFlag(true);
        flightRepository.save(flight);
    }

    public List<FlightDTO> getFlights() {

        List<Flight> flights = flightRepository.findAll();

        return flights.stream().map(messageMapper::toFlightDto).collect(Collectors.toList());
    }

    @Transactional
    public FlightAndSeat getFlightsWithSeats(Long id) {
        Flight flights = flightRepository.getById(id);

        return messageMapper.convertFlightToFlightAndSeatTest(flights);
    }

    public FlightDTO create(FlightCreate request) {
        Flight flight = new Flight();
        flight.setFlightName(request.getFlightName());
        flight.setDescription(request.getDescription());
        flight.setTakeOff(request.getTakeOff());
        flight.setLand(request.getLand());

        Flight saved = flightRepository.saveAndFlush(flight);
        log.info("Flight saved successfully!");

        return messageMapper.toFlightDto(saved);
    }

    public FlightDTO update(Long id, FlightUpdate request) throws NotFoundException {
        Flight flight = getFlight(id);
        flight.setFlightName(request.getFlightName());
        flight.setDescription(request.getDescription());

        flightRepository.save(flight);
        log.info("Flight updated successfully!");

        return messageMapper.toFlightDto(flight);//this.convertToDto(flight);
    }

    public Flight getFlight(Long id) throws NotFoundException {
        return flightRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}