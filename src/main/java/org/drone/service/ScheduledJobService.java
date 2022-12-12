package org.drone.service;

import org.drone.model.Drone;
import org.drone.model.DroneBatteryCapacityAudit;
import org.drone.repository.DroneBatteryCapacityAuditRepository;
import org.drone.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduledJobService {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DroneBatteryCapacityAuditRepository droneBatteryCapacityAuditRepository;

    @Scheduled(cron = "${app.drone-battery-capacity-scheduler-freq}")
    public void droneBatteryPeriodicCheck(){

        Iterable<Drone> drones = droneRepository.findAll();
        List<DroneBatteryCapacityAudit> droneBatteryCapacityAudits = new ArrayList<>();
        for (Drone drone: drones){
            DroneBatteryCapacityAudit droneBatteryCapacityAudit = new DroneBatteryCapacityAudit();
            droneBatteryCapacityAudit.setDroneId(drone.getId());
            droneBatteryCapacityAudit.setBatteryCapacity(drone.getBatteryCapacity());
            droneBatteryCapacityAudit.setDateTime(LocalDateTime.now());
            droneBatteryCapacityAudits.add(droneBatteryCapacityAudit);
        }
        droneBatteryCapacityAuditRepository.saveAll(droneBatteryCapacityAudits);
    }
}
