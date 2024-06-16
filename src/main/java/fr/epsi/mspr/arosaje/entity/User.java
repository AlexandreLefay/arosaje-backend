package fr.epsi.mspr.arosaje.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * The id of the user
     * Generated automatically
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The username of the user
     */
    private String username;
    /**
     * The firstname of the user
     */
    private String firstname;
    /**
     * The lastname of the user
     */
    private String lastname;
    /**
     * The email of the user
     */
    private String email;
    /**
     * The phone number of the user
     */
    private String phone;
    /**
     * The status of the user
     */
    private boolean active;

    /**
     * The auth0Id of the user for google authentification
     */
    private String auth0Id;

    /**
     * The user photo from google
     */
    private String photoUrl;

    /**
     * The address of the user
     * @Embedded is used to embed the Address class into the User class,
     * so that the fields of the Address class are columns in the users table,
     * avoiding the need for a separate table for addresses.
     */
    @Embedded
    private Address address;

    /**
     * The latitude and longitude of the user's location
     */
    private float x;
    private float y;
    /**
     * The password of the user
     */
    private String password;

    /**
     * The date when the user was created and updated
     * Inserted automatically
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * The roles of the user
     * Many users can have many roles
     * The user_roles table is the join table
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    /**
     * The list of roles of the user
     */
    private Set<Role> roles = new HashSet<>();

    /**
     * The photo of the user
     * One user can have one profile picture
     * The photo_profil_id column is the foreign key
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_profil_id", referencedColumnName = "id")
    private Photo photo;

}

