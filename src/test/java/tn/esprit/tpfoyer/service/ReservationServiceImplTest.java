package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.entity.Etudiant; // Assurez-vous d'importer l'entité Etudiant
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReservationServiceImplTest {

    @InjectMocks
    private ReservationServiceImpl reservationService; // Service à tester

    @Mock
    private ReservationRepository reservationRepository; // Repository moqué

    private Reservation reservation; // Instance de Reservation

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Créer une instance d'Etudiant pour le test
        Etudiant etudiant = new Etudiant(); // Définissez les propriétés nécessaires
        etudiant.setIdEtudiant(1L); // Exemple d'ID

        // Créer un ensemble d'étudiants pour la réservation
        Set<Etudiant> etudiants = new HashSet<>();
        etudiants.add(etudiant);

        // Créer une instance de Reservation avec tous les paramètres requis
        reservation = new Reservation();
        reservation.setIdReservation("1");
        reservation.setAnneeUniversitaire(new Date()); // Exemple de date
        reservation.setEstValide(true);
        reservation.setEtudiants(etudiants);
    }

    @Test
    public void testRetrieveAllReservations() {
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation));

        List<Reservation> result = reservationService.retrieveAllReservations(); // Appel de la méthode à tester

        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getIdReservation()); // Vérification de l'ID de la réservation
        verify(reservationRepository, times(1)).findAll(); // Vérification de l'appel au repository
    }

    @Test
    public void testRetrieveReservation() {
        when(reservationRepository.findById("1")).thenReturn(Optional.of(reservation));

        Reservation result = reservationService.retrieveReservation("1"); // Appel de la méthode à tester

        assertEquals("1", result.getIdReservation()); // Vérification de l'ID de la réservation
        verify(reservationRepository, times(1)).findById("1"); // Vérification de l'appel au repository
    }

    @Test
    public void testAddReservation() {
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = reservationService.addReservation(reservation); // Appel de la méthode à tester

        assertEquals("1", result.getIdReservation()); // Vérification de l'ID de la réservation
        verify(reservationRepository, times(1)).save(reservation); // Vérification de l'appel au repository
    }

    @Test
    public void testModifyReservation() {
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation result = reservationService.modifyReservation(reservation); // Appel de la méthode à tester

        assertEquals("1", result.getIdReservation()); // Vérification de l'ID de la réservation
        verify(reservationRepository, times(1)).save(reservation); // Vérification de l'appel au repository
    }

    @Test
    public void testRemoveReservation() {
        doNothing().when(reservationRepository).deleteById("1");

        reservationService.removeReservation("1"); // Appel de la méthode à tester

        verify(reservationRepository, times(1)).deleteById("1"); // Vérification de l'appel au repository
    }
}
