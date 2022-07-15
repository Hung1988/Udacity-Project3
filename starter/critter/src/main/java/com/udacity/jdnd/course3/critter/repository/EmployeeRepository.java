package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {
    @Query(value = "SELECT d.employee_id FROM days_available d WHERE d.days_available = :daysAvailable", nativeQuery = true)
    Set<Long> findAllByDaysAvailable (@Param("daysAvailable") String daysAvailable);

}
