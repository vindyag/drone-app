package org.drone.repository;

import org.drone.model.Drone;
import org.drone.model.DroneMedication;
import org.drone.model.DroneState;
import org.drone.model.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneMedicationRepository extends CrudRepository<DroneMedication,Long> {

    Iterable<DroneMedication> findAllByDroneId(Long droneId);

}
