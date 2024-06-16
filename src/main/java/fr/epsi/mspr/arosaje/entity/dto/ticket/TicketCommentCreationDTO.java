package fr.epsi.mspr.arosaje.entity.dto.ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for creating a new ticket comment.
 */
@Getter
@Setter
@NoArgsConstructor
public class TicketCommentCreationDTO {
    private String comment;
    private Long userId;
}