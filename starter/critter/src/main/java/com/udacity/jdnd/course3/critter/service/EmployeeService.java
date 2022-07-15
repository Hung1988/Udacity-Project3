package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmp (Employee employee) {
        if (employee != null) employeeRepository.save(employee);
        return employee;
    }

    public Employee getEpm (Long epmId) {
        Employee employee = employeeRepository.getOne(epmId);
        return employee;
    }

    public void updateDateAvailable (Set<DayOfWeek> days, Long id) {
        Employee employee = getEpm(id);
        employee.setDaysAvailable(days);
        saveEmp(employee);
    }

    public List<Employee> checkAvailable (EmployeeRequestDTO employeeRequestDTO) {
        List<Employee> employeeList = new ArrayList<>();
        Set<Long> ids = employeeRepository.findAllByDaysAvailable(employeeRequestDTO.getDate().getDayOfWeek().name());
        ids.forEach(id ->{
            if(employeeRepository.getOne(id).getSkills().containsAll(employeeRequestDTO.getSkills())) {
                employeeList.add(employeeRepository.getOne(id));
            }
        });
        return  employeeList;
    }
}
