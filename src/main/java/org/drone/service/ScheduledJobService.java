package org.drone.service;

import org.drone.dto.DroneActionRequestDTO;
import org.drone.model.Drone;
import org.drone.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledJobService {

    @Autowired
    private DroneRepository droneRepository;

    @Scheduled(cron = "${job.cron.fivesec}")
    public void droneBatteryPeriodicCheck(){

        Iterable<Drone> drones = droneRepository.findAll();
        for (Drone data: drones){
            DroneActionRequestDTO droneActionRequestDTO = new DroneActionRequestDTO();
        }
    }
}
