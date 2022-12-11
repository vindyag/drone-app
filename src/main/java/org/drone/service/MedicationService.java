package org.drone.service;

import org.drone.dto.MedicationDTO;
import org.drone.model.Drone;
import org.drone.model.DroneState;
import org.drone.model.Medication;
import org.drone.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    public List<MedicationDTO> loadMedications() {
        Iterable<Medication> medications = medicationRepository.findAll();
        List<MedicationDTO> medicationDTOS = new ArrayList<>();
        for(Medication medication: medications){
            MedicationDTO medicationDTO = new MedicationDTO();
            medicationDTO.setCode(medication.getCode());
            medicationDTO.setImage(medication.getImage());
            medicationDTO.setMedicineName(medication.getMedicine());
            medicationDTO.setWeight(medication.getWeight());
            medicationDTOS.add(medicationDTO);
        }
        return medicationDTOS;
    }

    public Long registerMedication(MedicationDTO medicationDTO) {
        Medication medication = new Medication();
        medication.setCode(medicationDTO.getCode());
        medication.setImage(medicationDTO.getImage());
        medication.setMedicine(medicationDTO.getMedicineName());
        medication.setWeight(medicationDTO.getWeight());
        Medication createdMedication = medicationRepository.save(medication);
        return createdMedication.getId();
    }
}
