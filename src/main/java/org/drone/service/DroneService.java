package org.drone.service;

import org.drone.dto.DroneAction;
import org.drone.dto.DroneActionRequestDTO;
import org.drone.dto.DroneBatteryCapacityDTO;
import org.drone.dto.DroneDTO;
import org.drone.model.Drone;
import org.drone.model.DroneMedication;
import org.drone.model.DroneState;
import org.drone.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DroneService {
    @Autowired
    private DroneRepository droneRepository;

    public Long registerDrone(DroneDTO droneDTO) {
        Drone drone = new Drone();
        drone.setModel(droneDTO.getModel());
        drone.setState(DroneState.IDLE);
        drone.setWeightLimit(droneDTO.getWeightLimit());
        if (droneDTO.getBatteryCapacity() != null) {
            drone.setBatteryCapacity(droneDTO.getBatteryCapacity().getBatteryCapacity());
        } else {
            drone.setBatteryCapacity(100.00);
        }
        Drone createdDrone = droneRepository.save(drone);
        return createdDrone.getId();
    }

    public Optional<Drone> loadDrone(Long id) {
        return droneRepository.findById(id);
    }

    public List<DroneDTO> loadDrones(Boolean availableForLoading) {

        Iterable<Drone> drones = null;
        if (availableForLoading != null) {
            if (availableForLoading) {
                drones = droneRepository.findAllByState(DroneState.IDLE);
            } else {
                List<DroneState> unavailableStatusList = new ArrayList<>();
                unavailableStatusList.add(DroneState.LOADING);
                unavailableStatusList.add(DroneState.LOADED);
                unavailableStatusList.add(DroneState.DELIVERING);
                unavailableStatusList.add(DroneState.DELIVERED);
                unavailableStatusList.add(DroneState.RETURNING);

                drones = droneRepository.findAllByStateList(unavailableStatusList);
            }
        }

        List<DroneDTO> droneDTOS = new ArrayList<>();

        if (drones != null) {
            for (Drone drone : drones) {
                DroneDTO droneDTO = new DroneDTO();
                droneDTO.setModel(drone.getModel());
                droneDTO.setState(drone.getState());
                droneDTO.setWeightLimit(drone.getWeightLimit());
                DroneBatteryCapacityDTO batteryCapacityDTO = new DroneBatteryCapacityDTO();
                batteryCapacityDTO.setBatteryCapacity(drone.getBatteryCapacity());
                droneDTO.setBatteryCapacity(batteryCapacityDTO);
                droneDTOS.add(droneDTO);
            }
        }

        return droneDTOS;
    }

    public void performDroneAction(Long droneId,DroneAction action, DroneActionRequestDTO request) {
        switch (action) {
            case LOAD -> loadDroneWithMedication(droneId, request.getMedicationIdToLoad());
            case UPDATE -> updateDroneStatus(droneId, request.getUpdatedDroneState());
        }
    }

    private void loadDroneWithMedication(Long droneId, Long medicationId) {
        DroneMedication droneMedication = new DroneMedication();
        droneMedication.setDroneId(droneId);
        droneMedication.setMedicationId(medicationId);
    }

    private void updateDroneStatus(Long droneId, DroneState newDroneState) {
        Optional<Drone> droneOpt = droneRepository.findById(droneId);
        droneOpt.ifPresent(drone -> drone.setState(newDroneState));
    }

    private void retrieveLoadedMedicationOnDrone(DroneActionRequestDTO request) {

    }

    private void retrieveBatteryLevelOfDrone(DroneActionRequestDTO request) {

    }

}
