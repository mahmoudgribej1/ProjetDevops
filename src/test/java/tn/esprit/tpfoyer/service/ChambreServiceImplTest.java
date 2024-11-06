package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ChambreServiceImplTest {

    @InjectMocks
    private ChambreServiceImpl chambreService;

    @Mock
    private ChambreRepository chambreRepository;

    private Chambre chambre;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        chambre = new Chambre(1L, 101, TypeChambre.SIMPLE, null, null);
    }

    @Test
    public void testRetrieveAllChambres() {
        when(chambreRepository.findAll()).thenReturn(Arrays.asList(chambre));

        List<Chambre> result = chambreService.retrieveAllChambres();

        assertEquals(1, result.size());
        assertEquals(101, result.get(0).getNumeroChambre());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    public void testRetrieveChambre() {
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        Chambre result = chambreService.retrieveChambre(1L);

        assertEquals(101, result.getNumeroChambre());
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    public void testRetrieveChambreNotFound() {
        when(chambreRepository.findById(1L)).thenReturn(Optional.empty());

        Chambre result = chambreService.retrieveChambre(1L);

        assertNull(result);
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddChambre() {
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre result = chambreService.addChambre(chambre);

        assertEquals(101, result.getNumeroChambre());
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    public void testModifyChambre() {
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre result = chambreService.modifyChambre(chambre);

        assertEquals(101, result.getNumeroChambre());
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    public void testRemoveChambre() {
        doNothing().when(chambreRepository).deleteById(1L);

        chambreService.removeChambre(1L);

        verify(chambreRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testRecupererChambresSelonTyp() {
        when(chambreRepository.findAllByTypeC(TypeChambre.SIMPLE)).thenReturn(Arrays.asList(chambre));

        List<Chambre> result = chambreService.recupererChambresSelonTyp(TypeChambre.SIMPLE);

        assertEquals(1, result.size());
        assertEquals(TypeChambre.SIMPLE, result.get(0).getTypeC());
        verify(chambreRepository, times(1)).findAllByTypeC(TypeChambre.SIMPLE);
    }

    @Test
    public void testTrouverChambreSelonEtudiant() {
        long cinEtudiant = 12345678L;
        when(chambreRepository.trouverChselonEt(cinEtudiant)).thenReturn(chambre);

        Chambre result = chambreService.trouverchambreSelonEtudiant(cinEtudiant);

        assertEquals(101, result.getNumeroChambre());
        verify(chambreRepository, times(1)).trouverChselonEt(cinEtudiant);
    }
}
