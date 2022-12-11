package org.drone.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serial_no_seq")
    @SequenceGenerator(name = "serial_no_seq", sequenceName = "serial_no_seq", allocationSize = 1)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Column(length = 500)
    private Integer weightLimit;

    @Column(length = 100)
    private Double batteryCapacity;

    @Enumerated(EnumType.STRING)
    private DroneState state;

}
