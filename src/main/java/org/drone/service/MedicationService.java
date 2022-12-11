package org.drone.service;

import org.drone.dto.DroneDTO;
import org.drone.dto.MedicationDTO;
import org.drone.model.Drone;
import org.drone.model.DroneState;
import org.drone.model.Medication;
import org.drone.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    public List<MedicationDTO> loadMedications() {
        Iterable<Medication> medications = medicationRepository.findAll();
        List<MedicationDTO> medicationDTOS = new ArrayList<>();
        for(Medication medication: medications){
            medicationDTOS.add(getMedicationDTOFromMedication(medication));
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

    public MedicationDTO getMedicationById(Long medicationId) {
        Optional<Medication> medicationOptional = medicationRepository.findById(medicationId);
        if(medicationOptional.isPresent()){
            return getMedicationDTOFromMedication(medicationOptional.get());
        }
        return null;
    }

    private MedicationDTO getMedicationDTOFromMedication(Medication medication) {
        MedicationDTO medicationDTO = new MedicationDTO();
        medicationDTO.setCode(medication.getCode());
        medicationDTO.setImage(medication.getImage());
        medicationDTO.setMedicineName(medication.getMedicine());
        medicationDTO.setWeight(medication.getWeight());
        return medicationDTO;
    }
}
