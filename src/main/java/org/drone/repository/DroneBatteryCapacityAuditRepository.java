package org.drone.repository;

import org.drone.model.DroneBatteryCapacityAudit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneBatteryCapacityAuditRepository extends CrudRepository<DroneBatteryCapacityAudit,Long> {

}
