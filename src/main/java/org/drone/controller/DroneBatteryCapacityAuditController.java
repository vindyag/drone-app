package org.drone.controller;

import org.drone.dto.DroneBatteryCapacityDTO;
import org.drone.service.DroneBatteryCapacityAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/drone-battery-capacity-audits", produces = {APPLICATION_JSON_VALUE})
public class DroneBatteryCapacityAuditController {

    @Autowired
    private DroneBatteryCapacityAuditService droneBatteryCapacityAuditService;

    @GetMapping
    public ResponseEntity<?> loadAllDroneBatteryCapacityAuditEntries(){
        List<DroneBatteryCapacityDTO> droneBatteryCapacityDTOs = droneBatteryCapacityAuditService.loadDroneBatteryCapacityAuditEntries();
        if(!droneBatteryCapacityDTOs.isEmpty()){
            return ResponseEntity.ok(droneBatteryCapacityDTOs);
        }
        return ResponseEntity.noContent().build();
    }
}
