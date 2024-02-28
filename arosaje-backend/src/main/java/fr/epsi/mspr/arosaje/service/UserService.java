package fr.epsi.mspr.arosaje.service;

import fr.epsi.mspr.arosaje.entity.User;
import fr.epsi.mspr.arosaje.entity.dto.user.UserDTO;
import fr.epsi.mspr.arosaje.entity.dto.user.UserSaveRequest;
import fr.epsi.mspr.arosaje.entity.mapper.UserMapper;
import fr.epsi.mspr.arosaje.exception.user.UserNotFoundException;
import fr.epsi.mspr.arosaje.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for handling user-related operations.
 */
@Slf4j
@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    /**
     * Error messages.
     */
    private static final String USER_NOT_FOUND = "No user found with id {}";
    private static final String USER_NOT_FOUND_USERNAME = "No user found with usernamme {}";

    private static final String USER_NOT_FOUND_email = "No user found with email {}";


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    /**
     * Creates a new user.
     *
     * @param userSaveRequest the user data to create
     * @return the created user as a UserDTO
     */
    public UserDTO createUser(UserSaveRequest userSaveRequest) {
        User user = userMapper.userSaveRequestToUser(userSaveRequest);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userMapper.userToUserDTO(userRepository.save(user));
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the ID of the user to retrieve
     * @return the retrieved user as a UserDTO
     * @throws UserNotFoundException if the user cannot be found
     */
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.info(USER_NOT_FOUND, id);
                    return new UserNotFoundException(id);
                });

        return userMapper.userToUserDTO(user);
    }

    /**
     * Updates a user with the provided UserSaveRequest.
     *
     * @param userSaveRequest the updated user data
     * @return the updated user as a UserDTO
     * @throws UserNotFoundException if the user cannot be found
     */
    public UserDTO updateUser(UserSaveRequest userSaveRequest) {
        User user = userRepository.findById(userSaveRequest.getId())
                .orElseThrow(() -> {
                    log.info(USER_NOT_FOUND, userSaveRequest.getId());
                    return new UserNotFoundException(userSaveRequest.getId());
                });

        userMapper.updateUserFromUserSaveRequest(userSaveRequest, user);
        user.setUpdatedAt(LocalDateTime.now());
        return userMapper.userToUserDTO(userRepository.save(user));
    }

    /**
     * Delete a user by his ID.
     *
     * @param id the ID of the user to delete
     * @throws UserNotFoundException if the user cannot be found
     */
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.info(USER_NOT_FOUND, id);
                    return new UserNotFoundException(id);
                });

        userRepository.delete(user);
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users as UserDTOs
     */
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    /**
     * Check if a user exists by his ID.
     *
     * @param id the ID of the user to check
     * @return true if the user exists, false otherwise
     */
    public boolean userExists(Long id) {
        return userRepository.existsById(id);
    }

    /**
     * Retrieves a user entity by his id.
     */
    public User getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.info(USER_NOT_FOUND, id);
                    return new UserNotFoundException(id);
                });
    }

    /**
     * Retrieves a user by email.
     *
     * @param email the email of the user to retrieve
     * @return the retrieved user as a UserDTO
     * @throws UserNotFoundException if the user cannot be found
     */
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.info(USER_NOT_FOUND_email, email);
                    return new UserNotFoundException(email, "email");
                });

        return userMapper.userToUserDTO(user);
    }

    /**
     * Retrieves a user by username.
     *
     * @param username the username of the user to retrieve
     * @return the retrieved user as a UserDTO
     * @throws UserNotFoundException if the user cannot be found
     */
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.info(USER_NOT_FOUND_USERNAME, username);
                    return new UserNotFoundException(username, "username");
                });

        return userMapper.userToUserDTO(user);
    }

}

