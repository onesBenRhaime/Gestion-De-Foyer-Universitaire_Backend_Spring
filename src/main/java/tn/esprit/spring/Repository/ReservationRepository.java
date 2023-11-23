package tn.esprit.spring.Repository;

import tn.esprit.spring.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository  extends JpaRepository<Reservation, Integer>{
}
