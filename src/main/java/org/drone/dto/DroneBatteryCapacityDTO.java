package org.drone.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class DroneBatteryCapacityDTO {
    private Long droneId;
    @Min(1)
    @Max(100)
    private Double batteryCapacity;
    private LocalDateTime dateTime;
}
