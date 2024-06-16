package fr.epsi.mspr.arosaje.service;

import fr.epsi.mspr.arosaje.entity.Photo;
import fr.epsi.mspr.arosaje.entity.Plant;
import fr.epsi.mspr.arosaje.entity.TicketComment;
import fr.epsi.mspr.arosaje.entity.dto.photo.PhotoSaveRequest;
import fr.epsi.mspr.arosaje.exception.plant.PlantNotFoundException;
import fr.epsi.mspr.arosaje.exception.user.UserNotFoundException;
import fr.epsi.mspr.arosaje.repository.PhotoRepository;
import fr.epsi.mspr.arosaje.repository.PlantRepository;
import fr.epsi.mspr.arosaje.repository.TicketCommentRepository;
import fr.epsi.mspr.arosaje.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Service used to handle photo upload requests
 * @Service annotation is used to indicate that the class is a service
 * @Slf4j annotation is used to automatically generate a logger field in the class
 */
@Service
@Slf4j
public class PhotoService {
    /**
     * Repositories for Photo and User entities.
     */
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    /**
     * Error messages.
     */
    private static final String PLANT_NOT_FOUND = "No plant found with id {}";
    private static final String USER_NOT_FOUND = "No user found with id {}";

    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private TicketCommentRepository ticketCommentRepository;


    public PhotoService(PhotoRepository photoRepository, UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    /**
     * Method used to save a photo in the database using multipart file
     *
     * @param photoSaveRequest
     * @throws IOException            if the file cannot be read
     * @throws RuntimeException       if the ticket comment is not found
     * @throws UserNotFoundException  if the user is not found
     * @throws PlantNotFoundException if the plant is not found
     */
    public void savePhoto(PhotoSaveRequest photoSaveRequest) throws IOException {
        Photo photo = new Photo();

        photo.setUser(userRepository.findById(photoSaveRequest.getUserId())
                .orElseThrow(() -> {
                    log.info(USER_NOT_FOUND, photoSaveRequest.getUserId());
                    return new UserNotFoundException(photoSaveRequest.getUserId());
                }));

        photo.setImageBlob(photoSaveRequest.getFile().getBytes());
        photo.setCreatedAt(LocalDateTime.now());

        if (photoSaveRequest.getPlantId() != null) {
            Plant plant = plantRepository.findById(photoSaveRequest.getPlantId())
                    .orElseThrow(() -> {
                        log.info(PLANT_NOT_FOUND, photoSaveRequest.getPlantId());
                        return new PlantNotFoundException(photoSaveRequest.getPlantId());
                    });
            photo.setPlant(plant);
        }

        if (photoSaveRequest.getTicketCommentId() != null) {
            TicketComment ticketComment = ticketCommentRepository.findById(photoSaveRequest.getTicketCommentId())
                    .orElseThrow(() -> new RuntimeException("Comment not found"));
            photo.setTicketComment(ticketComment);
        }

        photoRepository.save(photo);
    }
}
