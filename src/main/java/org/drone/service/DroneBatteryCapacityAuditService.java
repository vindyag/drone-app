package org.drone.service;

import org.drone.dto.DroneBatteryCapacityDTO;
import org.drone.model.DroneBatteryCapacityAudit;
import org.drone.repository.DroneBatteryCapacityAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DroneBatteryCapacityAuditService {
    @Autowired
    private DroneBatteryCapacityAuditRepository droneBatteryCapacityAuditRepository;

    public List<DroneBatteryCapacityDTO> loadDroneBatteryCapacityAuditEntries() {
        Iterable<DroneBatteryCapacityAudit> droneBatteryCapacityAudits = droneBatteryCapacityAuditRepository.findAll();
        List<DroneBatteryCapacityDTO> droneBatteryCapacityDTOs = new ArrayList<>();
        for(DroneBatteryCapacityAudit droneBatteryCapacityAudit: droneBatteryCapacityAudits){
            DroneBatteryCapacityDTO droneBatteryCapacityDTO = new DroneBatteryCapacityDTO();
            droneBatteryCapacityDTO.setDroneId(droneBatteryCapacityAudit.getDroneId());
            droneBatteryCapacityDTO.setBatteryCapacity(droneBatteryCapacityAudit.getBatteryCapacity());
            droneBatteryCapacityDTO.setDateTime(droneBatteryCapacityAudit.getDateTime());
            droneBatteryCapacityDTOs.add(droneBatteryCapacityDTO);
        }
        return droneBatteryCapacityDTOs;
    }
}
