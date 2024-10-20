package com.iyzico.challenge.flight.entity;


import com.iyzico.challenge.seat.dto.response.SeatCreate;
import com.iyzico.challenge.seat.dto.response.SeatDTO;
import com.iyzico.challenge.seat.entity.Seat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(schema = "PUBLIC",name = "FLIGHT")
public class Flight extends AbstractEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String flightName;

    @Column(name="description")
    private String description;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "flight")
    private List<Seat> seats;

    @Column(name="price")
    private double price;

    @Column(unique = true)
    private String callSign;

    private String takeOff;
    private String land;

    @PrePersist
    private void onInsert() {
        setCallSign(UUID.randomUUID().toString());
    }

}

