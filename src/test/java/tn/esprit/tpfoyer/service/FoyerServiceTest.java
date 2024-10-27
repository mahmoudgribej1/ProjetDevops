package tn.esprit.tpfoyer.service;

// Importation des classes nécessaires pour les tests
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Foyer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

public class FoyerServiceTest {

    // Déclaration d'une variable pour la classe Foyer
    private Foyer foyer;

    // Méthode exécutée avant chaque test
    @BeforeEach
    public void setUp() {
        // Initialisation d'un nouveau foyer pour chaque test
        foyer = new Foyer();
        foyer.setIdFoyer(1L); // Définit l'ID du foyer
        foyer.setNomFoyer("Foyer Test"); // Définit le nom du foyer
        foyer.setCapaciteFoyer(100); // Définit la capacité du foyer
        foyer.setBlocs(new HashSet<>()); // Initialise la collection des blocs
    }

    // Test pour vérifier l'ajout d'un bloc au foyer
    @Test
    public void testAjouterBloc() {
        // Création d'un nouveau bloc
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A"); // Définit le nom du bloc

        // Ajoute le bloc au foyer
        foyer.ajouterBloc(bloc);

        // Vérifie que le bloc a bien été ajouté à la collection des blocs du foyer
        assertTrue(foyer.getBlocs().contains(bloc), "Le bloc devrait être ajouté au foyer");
    }

    // Test pour vérifier la création d'un foyer
    @Test
    public void testCreationFoyer() {
        // Vérifie que l'ID du foyer n'est pas nul
        assertNotNull(foyer.getIdFoyer(), "L'ID du foyer ne doit pas être nul");
        // Vérifie que le nom du foyer est correct
        assertEquals("Foyer Test", foyer.getNomFoyer(), "Le nom du foyer doit correspondre");
        // Vérifie que la capacité du foyer est correcte
        assertEquals(100, foyer.getCapaciteFoyer(), "La capacité du foyer doit correspondre");
        // Vérifie que la liste des blocs est vide à la création
        assertTrue(foyer.getBlocs().isEmpty(), "La liste des blocs doit être vide à la création");
    }
}

