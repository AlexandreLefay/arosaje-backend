package fr.epsi.mspr.arosaje.entity.dto.photo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Using @Getter and @Setter from Lombok to automatically generate getters and setters for the fields in this class.
 */
@Getter
@Setter
public class PhotoDto {
    private Long id;
    private Long userId;
    private Long ticketCommentId;
    private Long plantId;
    private LocalDateTime createdAt;
    private byte[] imageBlob;
}
