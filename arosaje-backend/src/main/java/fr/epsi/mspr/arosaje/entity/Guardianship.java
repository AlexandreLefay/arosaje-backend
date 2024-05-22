package fr.epsi.mspr.arosaje.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;

@Entity
@Table(name = "guardianships")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Guardianship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "plant_id", referencedColumnName = "id")
    private Plant plant;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "guardian_user_id", referencedColumnName = "id")
    private User guardianUser;

    @ManyToOne
    @JoinColumn(name = "owner_user_id", referencedColumnName = "id")
    private User ownerUser;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true)
    private Status status;

    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
