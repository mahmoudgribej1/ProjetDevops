package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EtudiantServiceImplTest {

    private EtudiantServiceImpl etudiantService;
    private EtudiantRepository etudiantRepository;

    @BeforeEach
    public void setUp() {
        etudiantRepository = mock(EtudiantRepository.class);
        etudiantService = new EtudiantServiceImpl(etudiantRepository);
    }

    @Test
    public void testRetrieveAllEtudiants() {
        // Arrange
        Etudiant etudiant1 = new Etudiant();
        Etudiant etudiant2 = new Etudiant();
        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant1, etudiant2));

        // Act
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        // Assert
        assertEquals(2, etudiants.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    public void testRetrieveEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        // Act
        Etudiant result = etudiantService.retrieveEtudiant(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getIdEtudiant());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Act
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);

        // Assert
        assertNotNull(savedEtudiant);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    public void testModifyEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Act
        Etudiant modifiedEtudiant = etudiantService.modifyEtudiant(etudiant);

        // Assert
        assertNotNull(modifiedEtudiant);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    public void testRemoveEtudiant() {
        // Act
        etudiantService.removeEtudiant(1L);

        // Assert
        verify(etudiantRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testRecupererEtudiantParCin() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setCinEtudiant(12345678L);
        when(etudiantRepository.findEtudiantByCinEtudiant(12345678L)).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.recupererEtudiantParCin(12345678L);

        // Assert
        assertNotNull(result);
        assertEquals(12345678L, result.getCinEtudiant());
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(12345678L);
    }
}