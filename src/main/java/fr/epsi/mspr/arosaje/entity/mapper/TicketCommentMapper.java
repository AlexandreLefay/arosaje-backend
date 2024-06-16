package fr.epsi.mspr.arosaje.entity.mapper;

import fr.epsi.mspr.arosaje.entity.Photo;
import fr.epsi.mspr.arosaje.entity.TicketComment;
import fr.epsi.mspr.arosaje.entity.dto.photo.PhotoDto;
import fr.epsi.mspr.arosaje.entity.dto.ticket.TicketCommentCreationDTO;
import fr.epsi.mspr.arosaje.entity.dto.ticket.TicketCommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface TicketCommentMapper {
    TicketCommentMapper INSTANCE = Mappers.getMapper(TicketCommentMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "ticket.id", target = "ticketId")
    TicketCommentDTO ticketCommentToTicketCommentDTO(TicketComment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ticket", ignore = true)
    @Mapping(target = "user", ignore = true)
    TicketComment ticketCommentCreationDTOToTicketComment(TicketCommentCreationDTO commentDTO);

    /**
     * Converts a TicketComment entity to a TicketCommentDTO.
     *
     * @param photo The TicketComment entity to be converted.
     * @return The corresponding TicketCommentDTO.
     */
    PhotoDto photoToPhotoResponseDto(Photo photo);

    /**
     * Converts a set of TicketComment entities to a set of TicketCommentDTOs.
     *
     * @param photos The set of TicketComment entities to be converted.
     * @return The set of corresponding TicketCommentDTOs.
     */
    Set<PhotoDto> photoToPhotosResponseDtos(Set<Photo> photos);

}