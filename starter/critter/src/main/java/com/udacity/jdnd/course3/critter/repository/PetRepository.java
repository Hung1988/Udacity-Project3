package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository <Pet, Long> {
    List<Pet> findAllByOwnerId(Long ownerId);

    @Query(value = "SELECT p.pet_id FROM pet p WHERE p.owner_id = :id", nativeQuery = true)
    List<Long> findIdsPetByOwnerId(@Param("id") Long id);
}
