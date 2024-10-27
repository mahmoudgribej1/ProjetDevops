package tn.esprit.tpfoyer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Foyer {

    private static final Logger logger = LogManager.getLogger(Foyer.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idFoyer;

    String nomFoyer;
    long capaciteFoyer;

    @OneToOne(mappedBy = "foyer")
    @ToString.Exclude
    @JsonIgnore
    Universite universite;

    @OneToMany(mappedBy = "foyer")
            @JsonIgnore
            @ToString.Exclude
    Set<Bloc> blocs;
   public void ajouterBloc(Bloc bloc) {
        logger.info("Ajout d'un bloc au foyer : {}", bloc.getNomBloc());
        blocs.add(bloc);
        logger.info("Bloc ajouté avec succès : {}", bloc.getNomBloc());
    }

}

