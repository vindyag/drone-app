package org.drone.validation;

import org.drone.model.Drone;
import org.drone.model.Medication;
import org.drone.repository.DroneRepository;
import org.drone.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneMedicationValidator {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private MedicationRepository medicationRepository;

    public boolean canMedicationBeLoadedToDrone(Long droneId, List<Long> medicationIds) {
        Optional<Drone> droneOptional = droneRepository.findById(droneId);
        if (droneOptional.isPresent()) {
            Drone drone = droneOptional.get();
            List<Medication> medications = medicationRepository.findAllByIds(medicationIds);
            Integer totalWeightOfMedication = medications.stream().mapToInt(Medication::getWeight).sum();
            if (drone.getWeightLimit() >= totalWeightOfMedication && drone.getBatteryCapacity() >= 25) {
                return true;
            }
        }
        return false;

    }
}
