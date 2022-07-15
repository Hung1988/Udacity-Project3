package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {
    @Autowired
    PetRepository petRepository;

    public Pet savePet (Pet pet) {
        if (pet != null)petRepository.save(pet);
        return pet;
    }

    public Pet getPet (Long petId) {
        Pet pet = petRepository.getOne(petId);
        return pet;
    }

    public List<Pet> getPetsByCusId (Long ownerId) {
        List<Pet> petList = petRepository.findAllByOwnerId(ownerId);
        return petList;
    }

    public List<Pet> getAllPets () {
        List<Pet> petList = petRepository.findAll();
        return petList;
    }

    public  List<Long> findIdsPetByOwnerId (Long id) {
      List<Long> listIds = petRepository.findIdsPetByOwnerId(id);
      return listIds;
    };
}
