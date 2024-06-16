package fr.epsi.mspr.arosaje.entity.dto.plant;

import fr.epsi.mspr.arosaje.entity.dto.guardianship.validation.MandatoryGuardianshipId;
import fr.epsi.mspr.arosaje.entity.dto.guardianship.validation.OptionalGuardianshipId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlantSaveRequest {
    /**
     * The id of the plant.
     * Can be null if the plant is not yet created.
     * OptionalGuardianshipId is used to validate that the id is null.
     * MandatoryGuardianshipId is used to validate that the id is not null.
     */
    @Null(groups = {OptionalGuardianshipId.class})
    @NotNull(groups = {MandatoryGuardianshipId.class})
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
     * The id of the user who owns the plant.
     */
    private Long userId;
}
