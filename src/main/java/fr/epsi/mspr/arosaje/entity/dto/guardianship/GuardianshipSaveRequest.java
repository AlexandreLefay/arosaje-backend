package fr.epsi.mspr.arosaje.entity.dto.guardianship;

import fr.epsi.mspr.arosaje.entity.Status;
import fr.epsi.mspr.arosaje.entity.dto.guardianship.validation.MandatoryGuardianshipId;
import fr.epsi.mspr.arosaje.entity.dto.guardianship.validation.OptionalGuardianshipId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "anGuardianshipSaveRequest", setterPrefix = "set")
public class GuardianshipSaveRequest {

    @Null(groups = {OptionalGuardianshipId.class})
    @NotNull(groups = {MandatoryGuardianshipId.class})
    private Long id;

    private Long ownerId;

    @Null(groups = {OptionalGuardianshipId.class})
    @NotNull(groups = {MandatoryGuardianshipId.class})
    private Long guardianId;

    private Long plantId;

    private String title;

    private String description;

    private Date startDate;

    private Date endDate;

    private Long statusId;

}
