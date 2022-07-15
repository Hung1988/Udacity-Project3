package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.models.Customer;
import com.udacity.jdnd.course3.critter.models.Employee;
import com.udacity.jdnd.course3.critter.models.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());
        pet.setOwnerId(petDTO.getOwnerId());
        pet.setType(petDTO.getType());
        pet.setCustomer(customerService.getCus(petDTO.getOwnerId()));
        pet = petService.savePet(pet);
        return convertPetToPetDto(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetToPetDto(petService.getPet(petId));
    }

    @GetMapping("/pet")
    public List<PetDTO> getPets(){
        List<PetDTO> petDTOs = new ArrayList<>();
        List<Pet> petList = petService.getAllPets();
        petList.forEach( pet -> {
            petDTOs.add(convertPetToPetDto(pet));
                }
        );
        return petDTOs;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> petDTOs = new ArrayList<>();
        List<Pet> pets = petService.getPetsByCusId(ownerId);
        pets.forEach(pet -> {
            petDTOs.add(convertPetToPetDto(pet));
        });
        return  petDTOs;
    }

    private PetDTO convertPetToPetDto (Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        return petDTO;
    }
}
