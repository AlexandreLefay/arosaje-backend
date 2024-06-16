package fr.epsi.mspr.arosaje.entity.dto.photo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Using @Getter and @Setter from Lombok to automatically generate getters and setters for the fields in this class.
 */
@Getter
@Setter
public class PhotoSaveRequest {
    private Long id;
    private Long userId;
    private Long ticketCommentId;
    private Long plantId;
    private MultipartFile file;
}
