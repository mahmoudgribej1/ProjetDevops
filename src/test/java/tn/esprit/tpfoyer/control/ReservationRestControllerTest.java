package tn.esprit.tpfoyer.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReservationRestControllerTest {

    @InjectMocks
    private ReservationRestController reservationRestController; // Controller à tester

    @Mock
    private IReservationService reservationService; // Service moqué

    private Reservation reservation; // Instance de Reservation

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialisation d'un exemple de réservation
        reservation = new Reservation("1", new Date(), true, null);
    }

    @Test
    public void testGetReservations() {
        when(reservationService.retrieveAllReservations()).thenReturn(Arrays.asList(reservation));

        List<Reservation> result = reservationRestController.getReservations(); // Appel de la méthode à tester

        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getIdReservation()); // Vérification de l'ID de la réservation
        verify(reservationService, times(1)).retrieveAllReservations(); // Vérification de l'appel au service
    }

    @Test
    public void testRetrieveReservation() {
        when(reservationService.retrieveReservation("1")).thenReturn(reservation);

        Reservation result = reservationRestController.retrieveReservation("1"); // Appel de la méthode à tester

        assertEquals("1", result.getIdReservation()); // Vérification de l'ID de la réservation
        verify(reservationService, times(1)).retrieveReservation("1"); // Vérification de l'appel au service
    }

    @Test
    public void testAddReservation() {
        when(reservationService.addReservation(reservation)).thenReturn(reservation);

        Reservation result = reservationRestController.addReservation(reservation); // Appel de la méthode à tester

        assertEquals("1", result.getIdReservation()); // Vérification de l'ID de la réservation
        verify(reservationService, times(1)).addReservation(reservation); // Vérification de l'appel au service
    }

    @Test
    public void testRemoveReservation() {
        doNothing().when(reservationService).removeReservation("1");

        reservationRestController.removeReservation("1"); // Appel de la méthode à tester

        verify(reservationService, times(1)).removeReservation("1"); // Vérification de l'appel au service
    }

    @Test
    public void testModifyReservation() {
        when(reservationService.modifyReservation(reservation)).thenReturn(reservation);

        Reservation result = reservationRestController.modifyReservation(reservation); // Appel de la méthode à tester

        assertEquals("1", result.getIdReservation()); // Vérification de l'ID de la réservation
        verify(reservationService, times(1)).modifyReservation(reservation); // Vérification de l'appel au service
    }

    @Test
    public void testRetrieveReservationParDateEtStatus() {
        // Vous pouvez également tester la méthode `retrieveReservationParDateEtStatus` si nécessaire
    }
}
