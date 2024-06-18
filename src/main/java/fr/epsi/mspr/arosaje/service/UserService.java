package fr.epsi.mspr.arosaje.service;

import fr.epsi.mspr.arosaje.entity.User;
import fr.epsi.mspr.arosaje.entity.dto.login.LoginResponse;
import fr.epsi.mspr.arosaje.entity.dto.user.UserDTO;
import fr.epsi.mspr.arosaje.entity.dto.user.UserSaveRequest;
import fr.epsi.mspr.arosaje.entity.mapper.UserMapper;
import fr.epsi.mspr.arosaje.exception.user.UserNotFoundException;
import fr.epsi.mspr.arosaje.repository.UserRepository;
//import fr.epsi.mspr.arosaje.security.CustomUserDetails;
import fr.epsi.mspr.arosaje.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service for handling user-related operations.
 */
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;


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

    public User getOrCreateUser(Jwt jwt) {
        String auth0Id = jwt.getSubject();
        String username = jwt.getClaim("preferred_username");
        String picture = jwt.getClaim("picture");
        String name = jwt.getClaim("name");
        String email = jwt.getClaim("email");

        Optional<User> userOptional = userRepository.findByAuth0Id(auth0Id);
        User user = userOptional.orElseGet(() -> {
            User newUser = new User();
            newUser.setAuth0Id(auth0Id);
            newUser.setFirstname(name);
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setPhotoUrl(picture);
            return userRepository.save(newUser);
        });

        return user;
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

