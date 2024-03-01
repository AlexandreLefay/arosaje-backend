package fr.epsi.mspr.arosaje.controller;

import fr.epsi.mspr.arosaje.entity.dto.user.UserSaveRequest;
import fr.epsi.mspr.arosaje.entity.dto.user.UserDTO;
import fr.epsi.mspr.arosaje.entity.dto.user.validation.MandatoryUserId;
import fr.epsi.mspr.arosaje.entity.dto.user.validation.OptionalUserId;
import fr.epsi.mspr.arosaje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing users.
 * Provides endpoints for creating, retrieving, updating, and deleting users.
 */
@RestController
@CrossOrigin(origins = "http://localhost:19006")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Creates a new user..
     *
     * @param userSaveRequest The UserCreationDTO containing the new user's information.
     * @return ResponseEntity containing the created UserDTO.
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Validated(OptionalUserId.class)@RequestBody UserSaveRequest userSaveRequest) {
        UserDTO newUser = userService.createUser(userSaveRequest);
        return ResponseEntity.ok(newUser);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity containing the UserDTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Updates an existing user.
     *
     * @param id The ID of the user to update.
     * @param userSaveRequest The UserCreationDTO containing the updated information for the user.
     * @return ResponseEntity containing the updated UserDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Validated(MandatoryUserId.class) @RequestBody UserSaveRequest userSaveRequest) {
        UserDTO updatedUser = userService.updateUser(userSaveRequest);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete.
     * @return ResponseEntity indicating the operation's success status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves all users.
     *
     * @return ResponseEntity containing a list of UserDTOs.
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return ResponseEntity containing the UserDTO.
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String username) {
        UserDTO user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email The email of the user to retrieve.
     * @return ResponseEntity containing the UserDTO.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}