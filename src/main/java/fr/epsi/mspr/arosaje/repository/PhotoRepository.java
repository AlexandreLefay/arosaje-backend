package fr.epsi.mspr.arosaje.repository;

import fr.epsi.mspr.arosaje.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
