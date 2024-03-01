package fr.epsi.mspr.arosaje.service;


import fr.epsi.mspr.arosaje.entity.Guardianship;
import fr.epsi.mspr.arosaje.entity.Plant;
import fr.epsi.mspr.arosaje.entity.Status;
import fr.epsi.mspr.arosaje.entity.User;
import fr.epsi.mspr.arosaje.entity.dto.guardianship.GuardianshipDTO;
import fr.epsi.mspr.arosaje.entity.dto.guardianship.GuardianshipSaveRequest;
import fr.epsi.mspr.arosaje.entity.mapper.GuardianshipMapper;
import fr.epsi.mspr.arosaje.exception.guardianship.GuardianshipNotFoundException;
import fr.epsi.mspr.arosaje.exception.plant.PlantNotFoundException;
import fr.epsi.mspr.arosaje.exception.user.UserNotFoundException;
import fr.epsi.mspr.arosaje.repository.GuardianshipRepository;
import fr.epsi.mspr.arosaje.repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.random;

/**
 * Service for handling Guardianship data operations.
 */
@Slf4j
@Service
public class GuardianshipService {

    /**
     * Error messages.
     */
    private static final String GUARDIANSHIP_NOT_FOUND = "No guardianship found with id {}.";
    private static final String PLANT_NOT_FOUND = "No plant found with id {}";
    private static final String USER_NOT_FOUND = "No user found with id {}";
    /**
     * Repositories for Guardianship.
     * Service for handling User and plant data operations.
     */
    private GuardianshipRepository guardianshipRepository;
    private StatusRepository statusRepository;
    private GuardianshipMapper guardianshipMapper;
    /**
     * Add @lazy to avoid circular dependency and @Autowired to inject the service.
     */
    @Lazy
    @Autowired
    private PlantService plantService;
    @Lazy
    @Autowired
    private UserService userService;

    public GuardianshipService(GuardianshipRepository guardianshipRepository, StatusRepository statusRepository, GuardianshipMapper guardianshipMapper) {
        this.guardianshipRepository = guardianshipRepository;
        this.statusRepository = statusRepository;
        this.guardianshipMapper = guardianshipMapper;
    }

    /**
     * Retrieve all users.
     *
     * @return a list of all users.
     */
    public List<GuardianshipDTO> findAll() {
        List<Guardianship> guardianships = guardianshipRepository.findAll();

        return guardianships.stream()
                .map(guardianshipMapper::guardianshipToGuardianshipDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve all guardianships for a specific user based on the owner of the plant's owner id.
     *
     * @param userId The ID of the owner to retrieve guardianships for.
     * @return a list of guardianships for the specified user.
     * @throws UserNotFoundException if the user with the specified ID does not exist.
     */
    public List<GuardianshipDTO> findAllByOwnerUserId(Long userId) {

        if (!userService.userExists(userId)) {
            log.info(USER_NOT_FOUND, userId);
            throw new UserNotFoundException(userId);
        }

        List<Guardianship> guardianships = guardianshipRepository.findAllByOwnerUserId(userId);

        return guardianships.stream()
                .map(guardianshipMapper::guardianshipToGuardianshipDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve all guardianships for a specific user based on the guardian of the plant's guardian id.
     *
     * @param userId The ID of the guardian to retrieve guardianships for.
     * @return a list of guardianships for the specified user.
     * @throws UserNotFoundException if the user with the specified ID does not exist.
     */
    public List<GuardianshipDTO> findAllByGuardianUserId(Long userId) {

        if (!userService.userExists(userId)) {
            log.info(USER_NOT_FOUND, userId);
            throw new UserNotFoundException(userId);
        }

        List<Guardianship> guardianships = guardianshipRepository.findAllByGuardianUserId(userId);

        return guardianships.stream()
                .map(guardianshipMapper::guardianshipToGuardianshipDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a guardianship by its ID.
     *
     * @param id The ID of the guardianship to retrieve.
     * @return the guardianship with the specified ID.
     * @throws GuardianshipNotFoundException if the guardianship with the specified ID does not exist.
     */
    public GuardianshipDTO findById(Long id) {
        Guardianship guardianship = guardianshipRepository.findById(id)
                .orElseThrow(() -> {
                    log.info(GUARDIANSHIP_NOT_FOUND, id);
                    return new GuardianshipNotFoundException(id);
                });

        return guardianshipMapper.guardianshipToGuardianshipDTO(guardianship);
    }

    /**
     * Update an existing guardianship.
     *
     * @param guardianshipSaveRequest The GuardianshipDTO containing the updated information for the guardianship.
     * @return the updated guardianship.
     * @throws GuardianshipNotFoundException if the guardianship with the specified ID does not exist.
     */
    public GuardianshipDTO update(GuardianshipSaveRequest guardianshipSaveRequest) {

        Guardianship guardianship = guardianshipRepository.findById(guardianshipSaveRequest.getId()).orElseThrow(() -> {
            log.info(GUARDIANSHIP_NOT_FOUND, guardianshipSaveRequest.getId());
            return new GuardianshipNotFoundException(guardianshipSaveRequest.getId());
        });

        Status status = statusRepository.findById(guardianshipSaveRequest.getStatusId()).orElse(null);

        guardianship.setStatus(status);
        guardianshipMapper.updateGuardianshipFromGuardianshipSaveRequest(guardianshipSaveRequest, guardianship);
        return guardianshipMapper.guardianshipToGuardianshipDTO(guardianshipRepository.save(guardianship));
    }

    /**
     * Create a new guardianship.
     *
     * @param guardianshipSaveRequest The GuardianshipDTO containing the new guardianship's information.
     * @return the created guardianship.
     * @throws UserNotFoundException  if the user with the specified ID does not exist.
     * @throws PlantNotFoundException if the plant with the specified ID does not exist.
     */
    public GuardianshipDTO create(GuardianshipSaveRequest guardianshipSaveRequest) {
        Guardianship guardianship = guardianshipMapper.guardianshipSaveRequestToGuardianship(guardianshipSaveRequest);

        User owner = userService.getUserEntityById(guardianshipSaveRequest.getOwnerId());

        Plant plant = plantService.getPlantEntityById(guardianshipSaveRequest.getPlantId());

        guardianship.setOwnerUser(owner);
        guardianship.setPlant(plant);

        return guardianshipMapper.guardianshipToGuardianshipDTO(guardianshipRepository.save(guardianship));
    }

    /**
     * Delete a guardianship by its ID.
     *
     * @param id The ID of the guardianship to delete.
     * @throws GuardianshipNotFoundException if the guardianship with the specified ID does not exist.
     * @throws if                            the guardianship is currently active based on the status. TODO: Implement this.
     *                                                                             TODO: Error 500 because we can't delete a guardianship that currently has messages associated with it.
     */
    public void delete(Long id) {

        Guardianship guardianship = guardianshipRepository.findById(id).orElseThrow(() -> {
            log.info("Guardianship {} not found", id);
            return new GuardianshipNotFoundException(id);
        });

        guardianshipRepository.delete(guardianship);
        log.info("Guardianship {} has been deleted", id);
    }

    /**
     * Check if a plant in use by a guardianship.
     *
     * @param plantId The ID of the plant to check.
     * @return true if the plant is in use by a guardianship, false otherwise.
     */
    public boolean isPlantInUse(Long plantId) {
        return guardianshipRepository.existsByPlantId(plantId);
    }
}

