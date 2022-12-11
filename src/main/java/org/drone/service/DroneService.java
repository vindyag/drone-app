package org.drone.service;

import org.drone.dto.DroneAction;
import org.drone.dto.DroneActionRequestDTO;
import org.drone.dto.DroneBatteryCapacityDTO;
import org.drone.dto.DroneDTO;
import org.drone.dto.MedicationDTO;
import org.drone.model.Drone;
import org.drone.model.DroneMedication;
import org.drone.model.DroneState;
import org.drone.model.Medication;
import org.drone.repository.DroneMedicationRepository;
import org.drone.repository.DroneRepository;
import org.drone.validator.DroneMedicationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DroneService {
    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private DroneMedicationRepository droneMedicationRepository;
    @Autowired
    private MedicationService medicationService;

    @Autowired
    private DroneMedicationValidator droneMedicationValidator;


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

    public DroneDTO loadDrone(Long id) {
        Optional<Drone> droneOptional = droneRepository.findById(id);
        if (droneOptional.isPresent()) {
            return getDroneDTOFromDrone(droneOptional.get());
        }
        return null;
    }

    public DroneBatteryCapacityDTO getDroneBatteryCapacity(Long id) {
        Optional<Drone> droneOptional = droneRepository.findById(id);
        if (droneOptional.isPresent()) {
            DroneBatteryCapacityDTO batteryCapacityDTO = new DroneBatteryCapacityDTO();
            batteryCapacityDTO.setDroneId(id);
            batteryCapacityDTO.setBatteryCapacity(droneOptional.get().getBatteryCapacity());
            return batteryCapacityDTO;
        }
        return null;
    }

    public List<MedicationDTO> getMedicationLoadedToDrone(Long droneId) {
        Iterable<DroneMedication> droneMedications = droneMedicationRepository.findAllByDroneId(droneId);
        List<MedicationDTO> medicationDTOs = new ArrayList<>();
        for (DroneMedication droneMedication : droneMedications) {
            Long medicationId = droneMedication.getMedicationId();
            MedicationDTO medicationDTO = medicationService.getMedicationById(medicationId);
            medicationDTOs.add(medicationDTO);
        }
        return medicationDTOs;
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
                droneDTOS.add(getDroneDTOFromDrone(drone));
            }
        }

        return droneDTOS;
    }

    public boolean performDroneAction(Long droneId, DroneAction action, DroneDTO droneDTO) {
        switch (action) {
            case LOAD -> {
                return loadDroneWithMedication(droneId, droneDTO.getMedications());
            }
            case UPDATE -> {
                return updateDroneStatus(droneId, droneDTO.getState());
            }
        }
        return false;
    }

    private boolean loadDroneWithMedication(Long droneId, List<MedicationDTO> medicationDTOs) {

        List<Long> medicationIds = medicationDTOs.stream().map(MedicationDTO::getMedicationId).collect(Collectors.toList());
        boolean medicationCanBeLoadedToDrone = droneMedicationValidator.canMedicationBeLoadedToDrone(droneId, medicationIds);

        if (medicationCanBeLoadedToDrone) {
            for (MedicationDTO medicationDTO : medicationDTOs) {
                DroneMedication droneMedication = new DroneMedication();
                droneMedication.setDroneId(droneId);
                droneMedication.setMedicationId(medicationDTO.getMedicationId());
                droneMedicationRepository.save(droneMedication);
            }
            return true;
        }
        return false;
    }

    private boolean updateDroneStatus(Long droneId, DroneState newDroneState) {
        Optional<Drone> droneOpt = droneRepository.findById(droneId);
        if (droneOpt.isPresent()) {
            Drone drone = droneOpt.get();
            drone.setState(newDroneState);
            droneRepository.save(drone);
            return true;
        }
        return false;
    }

    private DroneDTO getDroneDTOFromDrone(Drone drone) {
        DroneDTO droneDTO = new DroneDTO();
        droneDTO.setModel(drone.getModel());
        droneDTO.setState(drone.getState());
        droneDTO.setWeightLimit(drone.getWeightLimit());
        DroneBatteryCapacityDTO batteryCapacityDTO = new DroneBatteryCapacityDTO();
        batteryCapacityDTO.setBatteryCapacity(drone.getBatteryCapacity());
        droneDTO.setBatteryCapacity(batteryCapacityDTO);
        return droneDTO;
    }

}
