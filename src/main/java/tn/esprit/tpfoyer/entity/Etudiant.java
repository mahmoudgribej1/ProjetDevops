package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Etudiant {

    private static final Logger logger = LogManager.getLogger(Etudiant.class); // Logger instance

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idEtudiant;

    String nomEtudiant;
    String prenomEtudiant;
    long cinEtudiant;
    Date dateNaissance;

    @ManyToMany(mappedBy = "etudiants")
    Set<Reservation> reservations;

    // Log creation of Etudiant instance
    @PostPersist
    public void logCreation() {
        logger.info("Created Etudiant: {}", this);
    }

    // Log when Etudiant instance is loaded from the database
    @PostLoad
    public void logLoad() {
        logger.info("Loaded Etudiant: {}", this);
    }

    // You can add additional methods for logging other actions if necessary
}

