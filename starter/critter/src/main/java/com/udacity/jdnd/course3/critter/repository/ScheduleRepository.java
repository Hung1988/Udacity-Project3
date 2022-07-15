package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository <Schedule, Long> {

    @Query(value = "SELECT s.employee_id FROM schedule_emp s WHERE s.schedule_id = :id", nativeQuery = true)
    List<Long> findIdsEmpByScheId (@Param("id") Long id);

    @Query(value = "SELECT s.schedule_id FROM schedule_emp s WHERE s.employee_id = :id", nativeQuery = true)
    List<Long> findIdsScheByEmpId (@Param("id") Long id);

    @Query(value = "SELECT s.pet_id FROM schedule_pet s WHERE s.schedule_id = :id", nativeQuery = true)
    List<Long> findIdsPetByScheId (@Param("id") Long id);

    @Query(value = "SELECT s.schedule_id FROM schedule_pet s WHERE s.pet_id = :id", nativeQuery = true)
    List<Long> findIdsScheByPetId (@Param("id") Long id);

    @Query(value = "SELECT s.schedule_id FROM schedule_pet s INNER JOIN pet e ON s.pet_id = e.pet_id WHERE e.owner_id = :id", nativeQuery = true)
    List<Long> findIdsScheByCusId (@Param("id") Long id);

}
