package com.iyzico.challenge.entity;



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
@Table(name = "FLIGHT")
public class Flight extends AbstractEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String flightName;

    @Column(name="description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE},
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

