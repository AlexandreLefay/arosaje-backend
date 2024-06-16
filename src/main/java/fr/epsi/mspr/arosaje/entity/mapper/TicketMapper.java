package fr.epsi.mspr.arosaje.entity.mapper;

import fr.epsi.mspr.arosaje.entity.Plant;
import fr.epsi.mspr.arosaje.entity.Ticket;
import fr.epsi.mspr.arosaje.entity.TicketComment;
import fr.epsi.mspr.arosaje.entity.dto.plant.PlantDto;
import fr.epsi.mspr.arosaje.entity.dto.ticket.TicketCommentDTO;
import fr.epsi.mspr.arosaje.entity.dto.ticket.TicketCreationDTO;
import fr.epsi.mspr.arosaje.entity.dto.ticket.TicketResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    /**
     * Converts a Ticket entity to a TicketResponseDTO, including its comments.
     *
     * @param ticket The Ticket entity to be converted.
     * @return The corresponding TicketResponseDTO.
     */
    @Mapping(source = "user.id", target = "userId")
    TicketResponseDTO ticketToTicketResponseDTO(Ticket ticket);

    /**
     * Converts a TicketComment entity to a TicketCommentDTO.
     *
     * @param plant The TicketComment entity to be converted.
     * @return The corresponding TicketCommentDTO.
     */
    @Mapping(source = "user.id", target = "userId")
    PlantDto plantToPlantResponseDto(Plant plant);

    /**
     * Converts a TicketComment entity to a TicketCommentDTO.
     *
     * @param comment The TicketComment entity to be converted.
     * @return The corresponding TicketCommentDTO.
     */
    @Mapping(source = "user.id", target = "userId")
    TicketCommentDTO ticketCommentToTicketCommentDTO(TicketComment comment);

    /**
     * Converts a set of TicketComment entities to a set of TicketCommentDTOs.
     *
     * @param comments The set of TicketComment entities to be converted.
     * @return The set of corresponding TicketCommentDTOs.
     */
    Set<TicketCommentDTO> ticketCommentsToTicketCommentDTOs(Set<TicketComment> comments);

    /**
     * Converts a TicketCreationDTO to a Ticket entity.
     *
     * @param ticketCreationDTO The TicketCreationDTO to be converted.
     * @return The corresponding Ticket entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    Ticket ticketCreationDTOToTicket(TicketCreationDTO ticketCreationDTO);
}
