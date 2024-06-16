package fr.epsi.mspr.arosaje.entity.dto.plant;

import fr.epsi.mspr.arosaje.entity.dto.photo.PhotoDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PlantDto {

    /**
     * The id of the plant.
     */
    private Long id;

    /**
     * The name of the plant.
     */
    private String name;

    /**
     * The species of the plant.
     */
    private String species;

    /**
     * The care instructions for the plant.
     */
    private String careInstructions;

    /**
     * The date and time the plant was created.
     */
    private LocalDateTime createdAt;

    /**
     * The date and time the plant was last updated.
     */
    private LocalDateTime updatedAt;

    /**
     * The id of the user who owns the plant.
     */
    private Long userId;

    /**
     * The photos of the plant.
     */
    private List<PhotoDto> photos;
}
