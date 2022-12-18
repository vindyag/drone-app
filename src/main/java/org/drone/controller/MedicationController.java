package org.drone.controller;

import org.drone.dto.MedicationDTO;
import org.drone.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Medication API")
@RestController
@RequestMapping(value = "api/v1/medications", produces = {APPLICATION_JSON_VALUE})
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @Operation(summary = "Add new Medication record")
    @PostMapping
    public ResponseEntity<?> registerMedication(@Valid @RequestBody MedicationDTO medicationDTO) {
        Long createdMedicationId = medicationService.registerMedication(medicationDTO);
        Map<String, Long> response = new HashMap<>();
        response.put("medicationId", createdMedicationId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all Medication Records")
    @GetMapping
    public ResponseEntity<?> loadAllMedication() {
        List<MedicationDTO> medicationDTOs = medicationService.loadMedications();
        if (!medicationDTOs.isEmpty()) {
            return ResponseEntity.ok(medicationDTOs);
        }
        return ResponseEntity.noContent().build();
    }
}
