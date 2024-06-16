package fr.epsi.mspr.arosaje.controller;

import fr.epsi.mspr.arosaje.entity.dto.photo.PhotoSaveRequest;
import fr.epsi.mspr.arosaje.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:19006")
@RestController
@RequestMapping("/api/photos")
@Slf4j
public class PhotoController {

    /**
     * Controller used to handle photo upload requests
     */
    private final PhotoService photoService;
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    /**
     * Method used to upload a photo
     *
     * @param file      MultipartFile containing the photo
     * @param userId    Id of the user who uploaded the photo
     * @param plantId   Id of the plant the photo is related to, can be null if the photo is related to a ticket comment
     * @param commentId Id of the ticket comment the photo is related to, can be null if the photo is related to a plant
     * @return ResponseEntity containing a message indicating the success or failure of the photo upload
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Long userId,
            @RequestParam(value = "plantId", required = false) Long plantId,
            @RequestParam(value = "commentId", required = false) Long commentId) {

        PhotoSaveRequest photoSaveRequest = new PhotoSaveRequest();
        photoSaveRequest.setFile(file);
        photoSaveRequest.setUserId(userId);
        photoSaveRequest.setPlantId(plantId);
        photoSaveRequest.setTicketCommentId(commentId);

        try {
            photoService.savePhoto(photoSaveRequest);
            return ResponseEntity.ok("Photo uploaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload photo");
        }
    }
}