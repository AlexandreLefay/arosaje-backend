package fr.epsi.mspr.arosaje.entity.dto.ticket;

import fr.epsi.mspr.arosaje.entity.dto.photo.PhotoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketCommentDTO {

    private Long id;
    private String comment;
    private LocalDateTime createdAt;
    private Long ticketId;
    private Long userId;
    private List<PhotoDto> photos;

}