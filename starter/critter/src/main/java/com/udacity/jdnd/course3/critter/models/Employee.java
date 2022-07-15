package com.udacity.jdnd.course3.critter.models;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
@Entity
@Table(name="EMPLOYEE")
public class Employee {
    @Id
    @Column(name = "EMPLOYEE_ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "EMPLOYEE_NAME", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "EMPLOYEE_SKILL")
    @ElementCollection(targetClass = EmployeeSkill.class)
    @JoinTable(name = "emp_skills", joinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @Column(name = "DAYS_AVAILABLE")
    @ElementCollection(targetClass = DayOfWeek.class)
    @JoinTable(name = "days_available", joinColumns = @JoinColumn(name = "EMPLOYEE_ID"))
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(mappedBy = "employeeList")
    private List<Schedule> scheduleList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
