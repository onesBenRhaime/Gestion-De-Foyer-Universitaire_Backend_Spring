package tn.esprit.spring.Service;

import tn.esprit.spring.entity.Reservation;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReservationServiceImpl {
    Reservation addReservation(Reservation reservation);
    Map<String, Object> ajouterReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Map<String, Object> getMesReservations(Long cinUser);
    Reservation updateReservation(Reservation reservation);
    void deleteReservation(Integer id);
    void nonValide(Integer id);
    Map<String, Object> estValide(Integer id);

    Optional<Reservation> getByIdReservation(Integer idReservation);
}
