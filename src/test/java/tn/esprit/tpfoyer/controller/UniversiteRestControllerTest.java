package tn.esprit.tpfoyer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.control.UniversiteRestController;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.service.IUniversiteService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UniversiteRestControllerTest {

    @InjectMocks
    private UniversiteRestController universiteRestController;

    @Mock
    private IUniversiteService universiteService;

    private Universite universite;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        universite = new Universite(1L, "Test University", "123 Test St", null);
    }

    @Test
    public void testGetUniversites() {
        when(universiteService.retrieveAllUniversites()).thenReturn(Arrays.asList(universite));

        List<Universite> result = universiteRestController.getUniversites();

        assertEquals(1, result.size());
        assertEquals("Test University", result.get(0).getNomUniversite());
        verify(universiteService, times(1)).retrieveAllUniversites();
    }

    @Test
    public void testRetrieveUniversite() {
        when(universiteService.retrieveUniversite(1L)).thenReturn(universite);

        Universite result = universiteRestController.retrieveUniversite(1L);

        assertEquals("Test University", result.getNomUniversite());
        verify(universiteService, times(1)).retrieveUniversite(1L);
    }

    @Test
    public void testAddUniversite() {
        when(universiteService.addUniversite(universite)).thenReturn(universite);

        Universite result = universiteRestController.addUniversite(universite);

        assertEquals("Test University", result.getNomUniversite());
        verify(universiteService, times(1)).addUniversite(universite);
    }

    @Test
    public void testRemoveUniversite() {
        doNothing().when(universiteService).removeUniversite(1L);

        universiteRestController.removeUniversite(1L);

        verify(universiteService, times(1)).removeUniversite(1L);
    }

    @Test
    public void testModifyUniversite() {
        when(universiteService.modifyUniversite(universite)).thenReturn(universite);

        Universite result = universiteRestController.modifyUniversite(universite);

        assertEquals("Test University", result.getNomUniversite());
        verify(universiteService, times(1)).modifyUniversite(universite);
    }
}
