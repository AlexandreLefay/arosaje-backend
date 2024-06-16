package fr.epsi.mspr.arosaje.entity.dto.ticket;

import fr.epsi.mspr.arosaje.entity.dto.plant.PlantDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private Set<TicketCommentDTO> comments;
    private Long userId;
    private PlantDto plant;


}
