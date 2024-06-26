package fr.epsi.mspr.arosaje.repository;

import fr.epsi.mspr.arosaje.entity.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * The repository for the plant entity.
 * This interface is used to interact with the database.
 */
public interface PlantRepository extends JpaRepository<Plant, Long> {
    Page<Plant> findByUserId(Long userId, Pageable pageable);
}
