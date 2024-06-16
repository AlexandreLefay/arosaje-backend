package fr.epsi.mspr.arosaje.entity.dto.user;

import fr.epsi.mspr.arosaje.entity.dto.adresse.AddressDTO;
import fr.epsi.mspr.arosaje.entity.dto.photo.PhotoDto;
import fr.epsi.mspr.arosaje.entity.dto.role.RoleDTO;
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
public class UserDTO {
    /**
     * The id of the user.
     */
    private int id;
    /**
     * The username of the user.
     */
    private String username;
    /**
     * The first name of the user.
     */
    private String firstname;
    /**
     * The last name of the user.
     */
    private String lastname;
    /**
     * The email of the user.
     */
    private String email;
    /**
     * The phone number of the user.
     */
    private String phone;
    /**
     * The address of the user.
     */
    private AddressDTO address;
    /**
     * The latitude of the user's location.
     */
    private float x;
    /**
     * The longitude of the user's location.
     */
    private float y;
    /**
     * The date and time the user was created.
     */
    private LocalDateTime createdAt;
    /**
     * The date and time the user was last updated.
     */
    private LocalDateTime updatedAt;
    /**
     * The roles of the user.
     */
    private Set<RoleDTO> roles;
    /**
     * The photos of the user.
     */
    private PhotoDto photo;

    /**
     * The auth0Id of the user for google authentification
     */
    private String auth0Id;

    /**
     * The user's photo from google
     */
    private String photoUrl;
}
