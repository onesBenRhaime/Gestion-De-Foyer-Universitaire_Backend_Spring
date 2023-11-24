package tn.esprit.spring.Service;

import tn.esprit.spring.entity.Reservation;

import java.util.List;
import java.util.Map;

public interface ReservationServiceImpl {
    Reservation addReservation(Reservation reservation);
    Map<String, Object> ajouterReservation(Reservation reservation);
    Map<String, Object> ajouterReservationV2(Reservation reservation);
    List<Reservation> getAllReservations();
    Reservation updateReservation(Reservation reservation);
    void deleteReservation(Integer id);
    void nonValide(Integer id);
    void ouiValide(Integer id);
    Map<String, Object> estValide(Integer id);
}
