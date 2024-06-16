package fr.epsi.mspr.arosaje.repository;

import fr.epsi.mspr.arosaje.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
