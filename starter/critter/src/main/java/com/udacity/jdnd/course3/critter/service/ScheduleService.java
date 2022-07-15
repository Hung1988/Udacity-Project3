package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule saveSchedule (Schedule schedule) {
        if (schedule != null) scheduleRepository.save(schedule);
        return schedule;
    }

    public List<Schedule> getAllSchedule () {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        return  scheduleList;
    }

    public List<Long> findIdsEmpByScheId (Long id) {
       List<Long> list = scheduleRepository.findIdsEmpByScheId(id);
       return list;
    };

    public List<Long> findIdsPetByScheId (Long id) {
        List<Long> list = scheduleRepository.findIdsPetByScheId(id);
        return list;
    };

    public List<Schedule> getScheduleForPet (Long id) {
        List<Long> scheduleIds = scheduleRepository.findIdsScheByPetId(id);
        List<Schedule> scheduleList = scheduleRepository.findAllById(scheduleIds);
        return scheduleList;
    };

    public List<Schedule> getScheduleForEmp(Long id) {
        List<Long> scheduleIds = scheduleRepository.findIdsScheByEmpId(id);
        List<Schedule> scheduleList = scheduleRepository.findAllById(scheduleIds);
        return scheduleList;
    };

    public List<Schedule> getScheduleForCus(Long id) {
        List<Long> scheduleIds = scheduleRepository.findIdsScheByCusId(id);
        List<Schedule> scheduleList = scheduleRepository.findAllById(scheduleIds);
        return scheduleList;
    };

}
