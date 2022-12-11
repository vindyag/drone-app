package org.drone.repository;

import org.drone.model.Medication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends CrudRepository<Medication,Long> {

    @Query( "select m from Medication m where m.id in :ids" )
    List<Medication> findAllByIds(@Param("ids") List<Long> ids);

}
