package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.models.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PetService petService;
    @PostMapping("/schedule")
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        List<Employee> employeeList = new ArrayList<>();
        List<Pet> petList = new ArrayList<>();
        if (!scheduleDTO.getPetIds().isEmpty()) {
                scheduleDTO.getPetIds().forEach(id ->{
                petList.add(petService.getPet(id));
                });}
        if (!scheduleDTO.getEmployeeIds().isEmpty()){
                scheduleDTO.getEmployeeIds().forEach(id ->{
                employeeList.add(employeeService.getEpm(id));
            });}
        schedule.setActivities(scheduleDTO.getActivities());
        schedule.setDate(scheduleDTO.getDate());
        schedule.setEmployeeList(employeeList);
        schedule.setPetList(petList);
        schedule = scheduleService.saveSchedule(schedule);
        ScheduleDTO newScheduleDTO = convertScheduleToScheduleDto(schedule);
        newScheduleDTO.setPetIds(scheduleDTO.getPetIds());
        newScheduleDTO.setEmployeeIds(scheduleDTO.getEmployeeIds());
        return newScheduleDTO;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> scheduleList = scheduleService.getAllSchedule();

        scheduleList.forEach ( schedule ->{
            ScheduleDTO scheduleDTO =convertScheduleToScheduleDto(schedule);
            scheduleDTO.setEmployeeIds(scheduleService.findIdsEmpByScheId(schedule.getId()));
            scheduleDTO.setPetIds(scheduleService.findIdsPetByScheId(schedule.getId()));
            scheduleDTOS.add(scheduleDTO);
        });
        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> scheduleList = scheduleService.getScheduleForPet(petId);
        ScheduleDTO scheduleDTO;
        for(Schedule schedule : scheduleList) {
            scheduleDTO = convertScheduleToScheduleDto(schedule);
            scheduleDTO.setPetIds(scheduleService.findIdsPetByScheId(schedule.getId()));
            scheduleDTO.setEmployeeIds(scheduleService.findIdsEmpByScheId(schedule.getId()));
            scheduleDTOS.add(scheduleDTO);
        };
        return scheduleDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> scheduleList = scheduleService.getScheduleForEmp(employeeId);
        ScheduleDTO scheduleDTO;
        for(Schedule schedule : scheduleList) {
            scheduleDTO = convertScheduleToScheduleDto(schedule);
            scheduleDTO.setPetIds(scheduleService.findIdsPetByScheId(schedule.getId()));
            scheduleDTO.setEmployeeIds(scheduleService.findIdsEmpByScheId(schedule.getId()));
            scheduleDTOS.add(scheduleDTO);
        };
        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> scheduleList = scheduleService.getScheduleForCus(customerId);
        ScheduleDTO scheduleDTO;
        for(Schedule schedule : scheduleList) {
            scheduleDTO = convertScheduleToScheduleDto(schedule);
            scheduleDTO.setPetIds(scheduleService.findIdsPetByScheId(schedule.getId()));
            scheduleDTO.setEmployeeIds(scheduleService.findIdsEmpByScheId(schedule.getId()));
            scheduleDTOS.add(scheduleDTO);
        };
        return scheduleDTOS;
    }

    private ScheduleDTO convertScheduleToScheduleDto (Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        return scheduleDTO;
    }
}
