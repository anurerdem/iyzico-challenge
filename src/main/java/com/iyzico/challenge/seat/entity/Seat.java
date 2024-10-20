package com.iyzico.challenge.seat.entity;

import com.iyzico.challenge.flight.entity.Flight;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table( schema = "PUBLIC",name = "SEAT")
public class Seat extends AbstractEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(name="seat_number")
    private String seatNumber;

    @Column(name="price")
    private BigDecimal seatPrice;

    private Boolean isSold = false;

    //private boolean isAvailable;
}

