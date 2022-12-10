package org.drone.service;

import org.drone.dto.DroneAction;
import org.drone.dto.DroneActionRequestDTO;
import org.drone.dto.DroneDTO;
import org.drone.dto.DroneFilterCriteria;
import org.drone.model.Drone;
import org.drone.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
public class DroneService {
    @Autowired
    private DroneRepository droneRepository;

    public void registerDrone(DroneDTO droneDTO) {
    }

    public Optional<Drone> loadDrone(Long id) {
        return droneRepository.findById(id);
    }

    public List<Drone> loadDrones(Boolean availableForLoading) {
        return null;
    }

    public void performDroneAction(DroneAction action, DroneActionRequestDTO request) {
        switch (action) {
            case LOAD -> loadDroneWithMedication(request);
            case UPDATE_STATUS -> updateDroneStatus(request);
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
