package fr.epsi.mspr.arosaje.repository;

import fr.epsi.mspr.arosaje.entity.Guardianship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuardianshipRepository extends JpaRepository<Guardianship, Long> {
    List<Guardianship> findAllByOwnerUserId(Long userId);

    List<Guardianship> findAllByGuardianUserId(Long userId);

    boolean existsByPlantId(Long plantId);

}
