package org.drone.controller;

import org.drone.dto.DroneAction;
import org.drone.dto.DroneActionRequestDTO;
import org.drone.dto.DroneRegistrationRequestDTO;
import org.drone.model.Drone;
import org.drone.service.DroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1/drones", produces = {APPLICATION_JSON_VALUE})
public class DroneController {
    private final DroneService droneService;

    @PostMapping
    public ResponseEntity<?> registerDrone(@Valid @RequestBody DroneRegistrationRequestDTO request) {
        droneService.registerDrone(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> loadDroneById(@Valid @PathVariable Long id) {
        Optional<Drone> drone = droneService.loadDrone(id);
        if(drone.isPresent()){
            return ResponseEntity.ok(drone);
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<?> performDroneAction(
        @RequestParam("action") DroneAction action, @Valid @RequestBody DroneActionRequestDTO request) {
        droneService.performDroneAction(action, request);
        return ResponseEntity.ok().build();
    }

}
