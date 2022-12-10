package org.drone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 100, unique = true)
    private Long serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Column(length = 500)
    private Integer weightLimit;

    @Min(1)
    @Max(100)
    @Column(length = 100)
    private Double batteryCapacity;

    @Enumerated(EnumType.STRING)
    private DroneState state;

}
