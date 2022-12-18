package org.drone.controller;

import org.drone.dto.DroneBatteryCapacityDTO;
import org.drone.service.DroneBatteryCapacityAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "DroneBatteryCapacityAudit API")
@RestController
@RequestMapping(value = "api/v1/drone-battery-capacity-audits", produces = {APPLICATION_JSON_VALUE})
public class DroneBatteryCapacityAuditController {

    @Autowired
    private DroneBatteryCapacityAuditService droneBatteryCapacityAuditService;

    @Operation(
        summary = "Get all Drone Battery Capacity Audit Records")
    @GetMapping
    public ResponseEntity<?> loadAllDroneBatteryCapacityAuditEntries(){
        List<DroneBatteryCapacityDTO> droneBatteryCapacityDTOs = droneBatteryCapacityAuditService.loadDroneBatteryCapacityAuditEntries();
        if(!droneBatteryCapacityDTOs.isEmpty()){
            return ResponseEntity.ok(droneBatteryCapacityDTOs);
        }
        return ResponseEntity.noContent().build();
    }
}
