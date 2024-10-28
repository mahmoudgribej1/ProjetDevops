package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UniversiteServiceImplTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    private Universite universite;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        universite = new Universite(1L, "Test University", "123 Test St", null);
    }

    @Test
    public void testRetrieveAllUniversites() {
        when(universiteRepository.findAll()).thenReturn(Arrays.asList(universite));

        List<Universite> result = universiteService.retrieveAllUniversites();

        assertEquals(1, result.size());
        assertEquals("Test University", result.get(0).getNomUniversite());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    public void testRetrieveUniversite() {
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(1L);

        assertEquals("Test University", result.getNomUniversite());
        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddUniversite() {
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);

        assertEquals("Test University", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testModifyUniversite() {
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.modifyUniversite(universite);

        assertEquals("Test University", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testRemoveUniversite() {
        doNothing().when(universiteRepository).deleteById(1L);

        universiteService.removeUniversite(1L);

        verify(universiteRepository, times(1)).deleteById(1L);
    }
}
