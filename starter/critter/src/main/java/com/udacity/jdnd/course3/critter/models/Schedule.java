package com.udacity.jdnd.course3.critter.models;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@Entity
@Table(name = "SCHEDULE")
public class Schedule {
    @Id
    @Column(name = "SCHEDULE_ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "ACTIVITIES")
    @ElementCollection(targetClass = EmployeeSkill.class)
    @JoinTable(name = "activities", joinColumns = @JoinColumn(name = "SCHEDULE_ID"))
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;
    @ManyToMany
    @JoinTable(name = "schedule_emp", joinColumns = @JoinColumn(name = "SCHEDULE_ID"), inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
    private List<Employee> employeeList;

    @ManyToMany
    @JoinTable(name = "schedule_pet", joinColumns = @JoinColumn(name = "SCHEDULE_ID"), inverseJoinColumns = @JoinColumn(name = "PET_ID"))
    private List<Pet> petList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }
}
