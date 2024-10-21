package com.iyzico.challenge.seat;

import com.iyzico.challenge.entity.Flight;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.enums.ErrorCode;
import com.iyzico.challenge.exceptions.SeatException;
import com.iyzico.challenge.flight.FlightService;
import com.iyzico.challenge.seat.dto.request.SeatCreateRequest;
import com.iyzico.challenge.seat.dto.request.SeatUpdateRequest;
import com.iyzico.challenge.seat.dto.response.SeatResponseResponse;
import com.iyzico.challenge.seat.repository.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SeatService {
    private final SeatRepository seatRepository;
    private final FlightService flightService;
    private final SeatMapper seatMapper;

    public SeatService(SeatRepository seatRepository, FlightService flightService, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.flightService = flightService;
        this.seatMapper = seatMapper;

    }

    @Transactional
    public List<SeatResponseResponse> addSeat(SeatCreateRequest request) {
        Flight flight = flightService.getFlight(request.getId());
        List<Seat> seats = request.getSeats().stream().map(seatMapper::toSeatEntity).collect(Collectors.toList());
        seats.forEach(p -> p.setFlight(flight));
        List<Seat> saved = seatRepository.saveAllAndFlush(seats);
        log.info(seats.size() + " seat added successfully!");
        return seatMapper.convertSeatEntityListToSeatDtoList(saved);
    }

    @Transactional
    public List<SeatResponseResponse> updateSeats(SeatUpdateRequest request) {
        List<SeatResponseResponse> seatsDTO = new ArrayList<>();
        List<Seat> entitySeats = new ArrayList<>();

        for (Long id : request.getIdPriceMap().keySet()) {
            Seat seat = getSeat(id);

            if (Boolean.TRUE.equals((seat.getIsSold()))) {
                throw new SeatException(ErrorCode.SEAT_ALREADY_SOLD);
            }

            seat.setSeatPrice(request.getIdPriceMap().get(id));
            entitySeats.add(seat);
            seatsDTO.add(seatMapper.toSeatDto(seat));
        }

        seatRepository.saveAll(entitySeats);
        log.info("Seats updated successfully!");
        return seatsDTO;
    }

    public List<SeatResponseResponse> getAvailableSeats(Long flightId) {
        Flight flight = flightService.getFlight(flightId);
        List<Seat> availableSeats = seatRepository.findSeatsByFlightAndIsSoldFalse(flight);

        if (availableSeats.isEmpty()) {
            log.error("No available seat found for flight: {}", flight.getCallSign());
            throw new SeatException(ErrorCode.AVAILABLE_SEAT_NOT_FOUND);
        }
        return seatMapper.convertSeatEntityListToSeatDtoList(availableSeats);
    }

    @Transactional
    public void deleteSeat(Long id) {
        Seat seat = this.getSeat(id);
        if (Boolean.TRUE.equals(seat.getIsSold())) {
            throw new SeatException(ErrorCode.SEAT_ALREADY_SOLD);
        }
        seat.setDeleteFlag(true);
        log.info("Seat information is deleted!");
        seatRepository.save(seat);
    }

    @Transactional
    public void updateSeatsStatus(List<Seat> seats) {
        List<Seat> entitySeats = new ArrayList<>();

        for (Seat seat : seats) {
            seat.setIsSold(true);
            entitySeats.add(seat);
        }

        seatRepository.saveAll(entitySeats);
        log.info("Seats status updated successfully!");
    }

    public Seat getSeat(Long id) {
        Optional<Seat> seatOptional = seatRepository.findById(id);
        if (seatOptional.isEmpty()) {
            log.error("No seat found for seat number: {}", id);
            throw new SeatException(ErrorCode.SEAT_NOT_FOUND);
        }
        return seatOptional.get();
    }

}