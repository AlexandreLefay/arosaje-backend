package fr.epsi.mspr.arosaje.entity.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketCreationDTO {
    private String title;
    private String description;
    private Long userId;
}
