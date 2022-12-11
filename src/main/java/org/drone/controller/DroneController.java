package org.drone.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;

import org.drone.dto.DroneAction;
import org.drone.dto.DroneActionRequestDTO;
import org.drone.dto.DroneDTO;
import org.drone.model.Drone;
import org.drone.service.DroneService;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/drones", produces = {APPLICATION_JSON_VALUE})
public class DroneController {
    @Autowired
    private DroneService droneService;

    @PostMapping
    public ResponseEntity<?> registerDrone(@Valid @RequestBody DroneDTO droneDTO) {
        Long createdDroneId = droneService.registerDrone(droneDTO);
        Map<String,Long> response = new HashMap<>();
        response.put("droneId", createdDroneId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> loadDronesByCriteria(@Valid @RequestParam Boolean availableForLoading ){
        List<DroneDTO> drones = droneService.loadDrones(availableForLoading);
        if(!drones.isEmpty()){
            return ResponseEntity.ok(drones);
        }
        return ResponseEntity.noContent().build();
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
