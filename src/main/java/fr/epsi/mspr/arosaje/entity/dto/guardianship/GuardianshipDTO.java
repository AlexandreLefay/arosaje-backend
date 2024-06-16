package fr.epsi.mspr.arosaje.entity.dto.guardianship;

import fr.epsi.mspr.arosaje.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "aGuardianshipDTO", setterPrefix = "set")
public class GuardianshipDTO {
    private Long id;

    private Long ownerId;

    private Long guardianId;

    private Long plantId;

    private Status status;

    private String title;

    private String description;

    private Date startDate;

    private Date endDate;

}
