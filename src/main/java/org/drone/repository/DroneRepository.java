package org.drone.repository;

import org.drone.model.Drone;
import org.drone.model.DroneState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends CrudRepository<Drone,Long> {
    Iterable<Drone> findAllByState(DroneState state);

    @Query( "select d from Drone d where d.state in :stateList" )
    Iterable<Drone> findAllByStateList(@Param("stateList") List<DroneState> stateList);
}
