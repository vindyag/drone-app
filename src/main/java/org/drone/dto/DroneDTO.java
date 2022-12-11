package org.drone.dto;

import org.drone.model.DroneModel;
import org.drone.model.DroneState;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DroneDTO {

    private DroneModel model;
    private Integer weightLimit;
    private DroneBatteryCapacityDTO batteryCapacity;
    private DroneState state;
    private List<MedicationDTO> medications;
}
