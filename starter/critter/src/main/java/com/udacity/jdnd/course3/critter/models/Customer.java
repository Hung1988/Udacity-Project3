package com.udacity.jdnd.course3.critter.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@Entity
@Table(name="CUSTOMER")
public class Customer {
    @Id
    @Column(name = "CUSTOMER_ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "CUSTOMER_NAME", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;
    @Column(name = "CUSTOMER_PHONE", nullable = false, columnDefinition = "VARCHAR(20)")
    private String phoneNumber;
    @Column(name = "NOTES", columnDefinition = "VARCHAR(255)")
    private String notes;
    @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL)
    @Column
    private List<Pet> pet;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPet() {
        return pet;
    }

    public void setPet(List<Pet> pet) {
        this.pet = pet;
    }

}
