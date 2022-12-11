package org.drone.dto;

import org.drone.model.DroneModel;
import org.drone.model.DroneState;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DroneDTO {
    @NotNull
    private DroneModel model;
    @NotNull
    private Integer weightLimit;
    private DroneBatteryCapacityDTO batteryCapacity;
    private DroneState state;
    private List<MedicationDTO> loadedMedications;
}
