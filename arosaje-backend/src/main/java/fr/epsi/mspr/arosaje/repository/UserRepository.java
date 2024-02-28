package fr.epsi.mspr.arosaje.repository;

import fr.epsi.mspr.arosaje.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The repository for the user entity.
 * This interface is used to interact with the database.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
