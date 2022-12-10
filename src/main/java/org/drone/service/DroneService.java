package org.drone.service;

import org.drone.dto.DroneAction;
import org.drone.dto.DroneActionRequestDTO;
import org.drone.dto.DroneRegistrationRequestDTO;
import org.drone.model.Drone;
import org.drone.repository.DroneRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DroneService {

    private final DroneRepository droneRepository;

    public void registerDrone(DroneRegistrationRequestDTO request) {
    }

    public Optional<Drone> loadDrone(Long id) {
        return droneRepository.findById(id);
    }

    public void performDroneAction(DroneAction action, DroneActionRequestDTO request) {
        switch (action) {
            case LOAD -> loadDroneWithMedication(request);
            case UPDATE_STATUS -> updateDroneStatus(request);
            case RETRIEVE_LOADED_MEDICATION -> retrieveLoadedMedicationOnDrone(request);
            case RETRIEVE_AVAILABLE_DRONES -> retrieveAvailableDrones(request);
            case RETRIEVE_BATTERY_LEVEL -> retrieveBatteryLevelOfDrone(request);
        }
    }

    private void loadDroneWithMedication(DroneActionRequestDTO request) {

    }

    private void updateDroneStatus(DroneActionRequestDTO request) {

    }

    private void retrieveLoadedMedicationOnDrone(DroneActionRequestDTO request) {

    }
    private void retrieveAvailableDrones(DroneActionRequestDTO request) {

    }
    private void retrieveBatteryLevelOfDrone(DroneActionRequestDTO request) {

    }

}
