package org.drone.dto;

import org.drone.model.DroneState;

import lombok.Data;

@Data
public class DroneActionRequestDTO {
    private Long medicationIdToLoad;
    private DroneState updatedDroneState;
}
