package org.drone.controller;

import org.drone.dto.DroneAction;
import org.drone.dto.DroneBatteryCapacityDTO;
import org.drone.dto.DroneDTO;
import org.drone.dto.MedicationDTO;
import org.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Drone API")
@RestController
@RequestMapping(value = "api/v1/drones", produces = {APPLICATION_JSON_VALUE})
public class DroneController {
    @Autowired
    private DroneService droneService;

    @Operation(summary = "Add new Drone record")
    @PostMapping
    public ResponseEntity<?> registerDrone(@Valid @RequestBody DroneDTO droneDTO) {
        Long createdDroneId = droneService.registerDrone(droneDTO);
        Map<String, Long> response = new HashMap<>();
        response.put("droneId", createdDroneId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get Drone entries by criteria. The only available criteria is availableForLoading.")
    @GetMapping
    public ResponseEntity<?> loadDronesByCriteria(@Valid @RequestParam Boolean availableForLoading) {
        List<DroneDTO> drones = droneService.loadDrones(availableForLoading);
        if (!drones.isEmpty()) {
            return ResponseEntity.ok(drones);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get Drone record by Drone Id")
    @GetMapping("/{id}")
    public ResponseEntity<?> loadDroneById(@Valid @PathVariable Long id) {
        DroneDTO droneDTO = droneService.loadDrone(id);
        if (droneDTO != null) {
            return ResponseEntity.ok(droneDTO);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get Drone Battery Capacity by Drone Id")
    @GetMapping("/{id}/battery-capacity")
    public ResponseEntity<?> getDroneBatteryCapacity(@Valid @PathVariable Long id) {
        DroneBatteryCapacityDTO droneBatteryCapacityDTO = droneService.getDroneBatteryCapacity(id);
        if (droneBatteryCapacityDTO != null) {
            return ResponseEntity.ok(droneBatteryCapacityDTO);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Get Medications loaded to Drone by Drone Id")
    @GetMapping("/{id}/medications")
    public ResponseEntity<?> getMedicationLoadedToDrone(@Valid @PathVariable Long id) {
        List<MedicationDTO> medicationDTOs = droneService.getMedicationLoadedToDrone(id);
        if (medicationDTOs != null) {
            return ResponseEntity.ok(medicationDTOs);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Perform Action on Drone. The only actions available as of now is LOAD and UPDATE. \n" +
            "LOAD is used to load the Drone with Medicine and UPDATE is used to update drone battery capacity and state.")

    @PatchMapping("/{id}")
    public ResponseEntity<?> performDroneAction(
        @Valid @PathVariable Long id,
        @RequestParam("action") DroneAction action,
        @Valid @RequestBody DroneDTO droneDTO) {
        boolean success = droneService.performDroneAction(id, action, droneDTO);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
