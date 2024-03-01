package fr.epsi.mspr.arosaje.controller;

import fr.epsi.mspr.arosaje.entity.dto.guardianship.GuardianshipDTO;
import fr.epsi.mspr.arosaje.entity.dto.guardianship.GuardianshipSaveRequest;
import fr.epsi.mspr.arosaje.entity.dto.guardianship.validation.MandatoryGuardianshipId;
import fr.epsi.mspr.arosaje.entity.dto.guardianship.validation.OptionalGuardianshipId;
import fr.epsi.mspr.arosaje.exception.guardianship.GuardianshipNotFoundException;
import fr.epsi.mspr.arosaje.exception.plant.PlantNotFoundException;
import fr.epsi.mspr.arosaje.exception.user.UserNotFoundException;
import fr.epsi.mspr.arosaje.service.GuardianshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing guardianships.
 */
@RestController
@CrossOrigin(origins = "http://localhost:19006")
@RequestMapping("/api/guardianships")
public class GuardianshipController {

    @Autowired
    private GuardianshipService guardianshipService;

    /**
     * Retrieves all guardianships.
     *
     * @return the list of guardianships.
     */
    @GetMapping()
    public List<GuardianshipDTO> getAllGuardianships() {
        return guardianshipService.findAll();
    }

    /**
     * Retrieves all guardianship for a specific user based on the owner of the plant's owner id.
     *
     * @param ownerUserId The ID of the owner to retrieve guardianships for.
     * @return the list of guardianships.
     * @throws UserNotFoundException if the user with the specified ID does not exist.
     */
    @GetMapping("/user/{ownerUserId}")
    public List<GuardianshipDTO> getAllGuardianshipsByOwnerUserId(Long ownerUserId) {
        return guardianshipService.findAllByOwnerUserId(ownerUserId);
    }

    /**
     * Retrieves all guardianship for a specific user based on the guardian of the plant's guardian id.
     *
     * @param guardianUserId The ID of the guardian to retrieve guardianships for.
     * @return the list of guardianships.
     * @throws UserNotFoundException if the user with the specified ID does not exist.
     */
    @GetMapping("/guardian/{guardianUserId}")
    public List<GuardianshipDTO> getAllGuardianshipsByGuardianUserId(Long guardianUserId) {
        return guardianshipService.findAllByGuardianUserId(guardianUserId);
    }

    /**
     * Retrieves a guardianship by its ID.
     *
     * @param id The ID of the guardianship to retrieve.
     * @return the guardianship with the specified ID.
     * @throws GuardianshipNotFoundException if the guardianship with the specified ID does not exist.
     */
    @GetMapping("/{id}")
    public GuardianshipDTO getGuardianshipById(Long id) {
        return guardianshipService.findById(id);
    }

    /**
     * Updates a guardianship by its ID.
     *
     * @param guardianshipSaveRequest The GuardianshipDTO containing the updated guardianship's information.
     * @return the updated GuardianshipDTO.
     * @throws GuardianshipNotFoundException if the guardianship with the specified ID does not exist.
     */
    @PutMapping("/{id}")
    public GuardianshipDTO updateGuardianship(@Validated(MandatoryGuardianshipId.class) @RequestBody GuardianshipSaveRequest guardianshipSaveRequest) {
        return guardianshipService.update(guardianshipSaveRequest);
    }

    /**
     * Creates a new guardianship.
     *
     * @param guardianshipSaveRequest The GuardianshipDTO containing the new guardianship's information.
     * @return the created GuardianshipDTO.
     * @throws UserNotFoundException  if the user with the specified ID does not exist.
     * @throws PlantNotFoundException if the plant with the specified ID does not exist.
     */
    @PostMapping
    public GuardianshipDTO createGuardianship(@Validated(OptionalGuardianshipId.class) @RequestBody GuardianshipSaveRequest guardianshipSaveRequest) {
        return guardianshipService.create(guardianshipSaveRequest);
    }

    /**
     * Deletes a guardianship by its ID.
     * @param id The ID of the guardianship to delete.
     * @throws GuardianshipNotFoundException if the guardianship with the specified ID does not exist.
     */
    @DeleteMapping("/{id}")
    public void deleteGuardianship(@PathVariable Long id) {
        guardianshipService.delete(id);
    }
}
