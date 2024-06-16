package fr.epsi.mspr.arosaje.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "plants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plant {

    /**
     * The id of the plant
     * Generated automatically
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The user who owns the plant
     * Many plants can belong to one user
     * The user_id column is the foreign key
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /**
     * The name of the plant
     */
    private String name;

    /**
     * The species of the plant
     */
    private String species;
    @Column(name = "care_instructions", columnDefinition = "TEXT")
    private String careInstructions;

    /**
     * The date when the plant was created
     * Inserted automatically
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * The date when the plant was last updated
     * Inserted automatically
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * The list of photos of the plant
     * One plant can have many photos
     * The plante_id column is the foreign key
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "plant_id")
    private List<Photo> photos;
}


