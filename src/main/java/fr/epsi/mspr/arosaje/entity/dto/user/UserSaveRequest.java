package fr.epsi.mspr.arosaje.entity.dto.user;

import fr.epsi.mspr.arosaje.entity.Photo;
import fr.epsi.mspr.arosaje.entity.dto.adresse.AddressDTO;
import fr.epsi.mspr.arosaje.entity.dto.guardianship.validation.MandatoryGuardianshipId;
import fr.epsi.mspr.arosaje.entity.dto.guardianship.validation.OptionalGuardianshipId;
import fr.epsi.mspr.arosaje.entity.dto.user.validation.MandatoryUserId;
import fr.epsi.mspr.arosaje.entity.dto.user.validation.OptionalUserId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequest {
    /**
     * The user id.
     * Use for creation and update.
     * If null, the user will be created.
     * If not null, the user will be updated.
     */
    @Null(groups = { OptionalUserId.class})
    @NotNull(groups = { MandatoryUserId.class})
    private Long id;
    /**
     * The user username.
     */
    private String username;
    /**
     * The user firstname.
     */
    private String firstname;
    /**
     * The user lastname.
     */
    private String lastname;
    /**
     * The user email.
     */
    private String email;
    /**
     * The user phone.
     */
    private String phone;
    /**
     * The user adress.
     */
    private AddressDTO address;
    /**
     * The user location.
     */
    private float x;
    private float y;

    /**
     * The auth0Id of the user for google authentification
     */
    private String auth0Id;

    /**
     * The user's photo from google
     */
    private String photoUrl;
}
