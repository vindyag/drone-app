package org.drone.controller;

import org.drone.dto.MedicationDTO;
import org.drone.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/medications", produces = {APPLICATION_JSON_VALUE})
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @GetMapping
    public ResponseEntity<?> loadAllMedication(){
        List<MedicationDTO> medicationDTOS = medicationService.loadMedications();
        if(!medicationDTOS.isEmpty()){
            return ResponseEntity.ok(medicationDTOS);
        }
        return ResponseEntity.noContent().build();
    }
}
