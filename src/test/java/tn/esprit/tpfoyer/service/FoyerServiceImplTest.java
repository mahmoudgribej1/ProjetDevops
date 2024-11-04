package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoyerServiceImplTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    private Foyer foyer;

    @BeforeEach
    void setUp() {
        foyer = new Foyer();
        foyer.setIdFoyer(1L);
        foyer.setNomFoyer("Test Foyer");
        foyer.setCapaciteFoyer(100);
    }

    @Test
    void testRetrieveAllFoyers() {
        List<Foyer> foyerList = new ArrayList<>();
        foyerList.add(foyer);

        when(foyerRepository.findAll()).thenReturn(foyerList);

        List<Foyer> result = foyerService.retrieveAllFoyers();

        assertEquals(1, result.size());
        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveFoyer() {
        when(foyerRepository.findById(anyLong())).thenReturn(Optional.of(foyer));

        Foyer result = foyerService.retrieveFoyer(1L);

        assertNotNull(result);
        assertEquals("Test Foyer", result.getNomFoyer());
        verify(foyerRepository, times(1)).findById(anyLong());
    }

    @Test
    void testAddFoyer() {
        when(foyerRepository.save(any(Foyer.class))).thenReturn(foyer);

        Foyer result = foyerService.addFoyer(foyer);

        assertNotNull(result);
        assertEquals("Test Foyer", result.getNomFoyer());
        verify(foyerRepository, times(1)).save(any(Foyer.class));
    }

    @Test
    void testModifyFoyer() {
        when(foyerRepository.save(any(Foyer.class))).thenReturn(foyer);

        Foyer result = foyerService.modifyFoyer(foyer);

        assertNotNull(result);
        assertEquals("Test Foyer", result.getNomFoyer());
        verify(foyerRepository, times(1)).save(any(Foyer.class));
    }

    @Test
    void testRemoveFoyer() {
        doNothing().when(foyerRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> foyerService.removeFoyer(1L));
        verify(foyerRepository, times(1)).deleteById(anyLong());
    }
}
