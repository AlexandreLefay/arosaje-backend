package fr.epsi.mspr.arosaje.entity.mapper;

import fr.epsi.mspr.arosaje.entity.Photo;
import fr.epsi.mspr.arosaje.entity.Plant;
import fr.epsi.mspr.arosaje.entity.dto.photo.PhotoDto;
import fr.epsi.mspr.arosaje.entity.dto.plant.PlantDto;
import fr.epsi.mspr.arosaje.entity.dto.plant.PlantSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @Mapper annotation is used to indicate that the class is a mapper
 * @ComponentModel annotation is used to indicate that the mapper should be a Spring component
 *
 * The PlantMapper interface is used to convert Plant entities to PlantDTOs and vice versa.
 */
@Mapper(componentModel = "spring")
public interface PlantMapper {

    Plant plantSaveRequestToPlant(PlantSaveRequest plantSaveRequest);

    @Mapping(source = "careInstructions", target = "careInstructions")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "photos", target = "photos")
    PlantDto plantToPlantResponseDto(Plant plant);

    /**
     * Converts a TicketComment entity to a TicketCommentDTO.
     *
     * @param photo The TicketComment entity to be converted.
     * @return The corresponding TicketCommentDTO.
     */
    PhotoDto photoToPhotoResponseDto(Photo photo);

    void updatePlantFromPlantSaveRequest(PlantSaveRequest plantCreationDto, @MappingTarget Plant plant);

    // mapping for userId
    void updateUserId(Long userId, @MappingTarget Plant plant);
}
