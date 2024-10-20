package com.iyzico.challenge.seat.repository;

import com.iyzico.challenge.flight.entity.Flight;
import com.iyzico.challenge.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findSeatsByFlightAndIsSoldFalse(Flight flight);

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Seat> findById(Long id);
}

