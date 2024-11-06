package tn.esprit.tpfoyer.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;
import tn.esprit.tpfoyer.service.IChambreService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChambreRestControllerTest {

    @InjectMocks
    private ChambreRestController chambreRestController;

    private ChambreServiceImpl chambreServiceImp ;

    @Mock
    private IChambreService chambreService;

    private Chambre chambre;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        chambre = new Chambre(1L, 101, TypeChambre.SIMPLE, null, null); // Initialisez une instance de Chambre
    }

    @Test
    public void testGetChambres() {
        when(chambreService.retrieveAllChambres()).thenReturn(Arrays.asList(chambre));

        List<Chambre> result = chambreRestController.getChambres();

        assertEquals(1, result.size());
        assertEquals(101, result.get(0).getNumeroChambre());
        verify(chambreService, times(1)).retrieveAllChambres();
    }

    @Test
    public void testRetrieveChambre() {
        when(chambreService.retrieveChambre(1L)).thenReturn(chambre);

        Chambre result = chambreRestController.retrieveChambre(1L);

        assertEquals(101, result.getNumeroChambre());
        assertEquals(TypeChambre.SIMPLE, result.getTypeC());
        verify(chambreService, times(1)).retrieveChambre(1L);
    }

    @Test
    public void testAddChambre() {
        when(chambreService.addChambre(chambre)).thenReturn(chambre);

        Chambre result = chambreRestController.addChambre(chambre);

        assertEquals(101, result.getNumeroChambre());
        verify(chambreService, times(1)).addChambre(chambre);
    }

    @Test
    public void testRemoveChambre() {
        doNothing().when(chambreService).removeChambre(1L);

        chambreRestController.removeChambre(1L);

        verify(chambreService, times(1)).removeChambre(1L);
    }

    @Test
    public void testModifyChambre() {
        when(chambreService.modifyChambre(chambre)).thenReturn(chambre);

        Chambre result = chambreRestController.modifyChambre(chambre);

        assertEquals(101, result.getNumeroChambre());
        verify(chambreService, times(1)).modifyChambre(chambre);
    }

    @Test
    public void testRecupererChambresSelonTyp() {
        when(chambreService.recupererChambresSelonTyp(TypeChambre.SIMPLE)).thenReturn(Arrays.asList(chambre));

        List<Chambre> result = chambreRestController.trouverChSelonTC(TypeChambre.SIMPLE);

        assertEquals(1, result.size());
        assertEquals(TypeChambre.SIMPLE, result.get(0).getTypeC());
        verify(chambreService, times(1)).recupererChambresSelonTyp(TypeChambre.SIMPLE);
    }


    @Test
    public void testTrouverChambreSelonEtudiant() {
        long cinEtudiant = 12345678L;
        when(chambreService.trouverchambreSelonEtudiant(cinEtudiant)).thenReturn(chambre);

        Chambre result = chambreRestController.trouverChSelonEt(cinEtudiant);

        assertEquals(101, result.getNumeroChambre());
        verify(chambreService, times(1)).trouverchambreSelonEtudiant(cinEtudiant);
    }

}
