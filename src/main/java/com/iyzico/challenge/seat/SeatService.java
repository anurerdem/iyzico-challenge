package com.iyzico.challenge.seat;


import com.iyzico.challenge.controller.MessageMapper;
import com.iyzico.challenge.seat.dto.request.SeatCreateRequest;
import com.iyzico.challenge.seat.dto.request.SeatUpdateRequest;
import com.iyzico.challenge.exceptions.NotFoundException;
import com.iyzico.challenge.exceptions.SeatException;
import com.iyzico.challenge.flight.FlightService;
import com.iyzico.challenge.flight.entity.Flight;
import com.iyzico.challenge.seat.dto.response.SeatDTO;
import com.iyzico.challenge.seat.entity.Seat;
import com.iyzico.challenge.seat.repository.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SeatService {
    private final SeatRepository seatRepository;
    private final FlightService flightService;
    private final MessageMapper messageMapper;

    public SeatService(SeatRepository seatRepository, FlightService flightService, MessageMapper messageMapper) {
        this.seatRepository = seatRepository;
        this.flightService = flightService;
        this.messageMapper = messageMapper;

    }

    @Transactional
    public List<SeatDTO> add(SeatCreateRequest request) throws NotFoundException {
        Flight flight = flightService.getFlight(request.getId());
        List<Seat> seats = request.getSeats().stream().map(messageMapper::toSeatEntity).collect(Collectors.toList());
        seats.forEach(p -> p.setFlight(flight));
        List<Seat> saved= seatRepository.saveAllAndFlush(seats);
        log.info(seats.size() + " seat added successfully!");
        return messageMapper.convertSeatEntityListToSeatDtoList(saved);
    }

    @Transactional
    public List<SeatDTO> updateSeats(SeatUpdateRequest request) throws NotFoundException, SeatException {
        List<SeatDTO> seatsDTO = new ArrayList<>();
        List<Seat> entitySeats = new ArrayList<>();

        for(Long id : request.getIdPriceMap().keySet()){
            Seat seat = getSeat(id);

            if (Boolean.TRUE.equals((seat.getIsSold()))) {
                throw new SeatException();
            }

            seat.setSeatPrice(request.getIdPriceMap().get(id));
            entitySeats.add(seat);
            seatsDTO.add(messageMapper.toSeatDto(seat));
        }

        seatRepository.saveAll(entitySeats);
        log.info("Seats updated successfully!");
        return seatsDTO;
    }

    public List<SeatDTO> getAvailableSeats(Long flightId) throws NotFoundException {
        Flight flight = flightService.getFlight(flightId);
        List<Seat> availableSeats = seatRepository.findSeatsByFlightAndIsSoldFalse(flight);

        if (availableSeats.isEmpty()) {
            log.error("No available seat found for flight: {}", flight.getCallSign());
            throw new NotFoundException();
        }
        return messageMapper.convertSeatEntityListToSeatDtoList(availableSeats);
    }

    public void delete(Long id) throws NotFoundException, SeatException {
        Seat seat = this.getSeat(id);
        if(Boolean.TRUE.equals(seat.getIsSold())){
            throw new SeatException();
        }
        seat.setDeleteFlag(true);
        seatRepository.save(seat);
    }

    @Transactional
    public void updateSeatsStatus(List<Seat> seats) {
        List<Seat> entitySeats = new ArrayList<>();

        for(Seat seat : seats){
            seat.setIsSold(true);
            entitySeats.add(seat);
        }

        seatRepository.saveAll(entitySeats);
        log.info("Seats status updated successfully!");
    }

    public Seat getSeat(Long id) throws NotFoundException {
        return seatRepository.findById(id).orElseThrow(NotFoundException::new);
    }

}