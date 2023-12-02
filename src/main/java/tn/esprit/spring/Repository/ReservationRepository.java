package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Universite;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository  extends JpaRepository<Reservation, Integer>{
    @Query("SELECT u FROM Reservation  u WHERE u.cinEtudiant = :cinEtudiant")
    List<Reservation> findAllByCinEtudiant(@Param("cinEtudiant") Long cinEtudiant);
    Optional<Reservation> findByCinEtudiantAndAnneeUniversitaire(Long cinEtudiant, int anneeUniversitaire);
 Reservation findByCinEtudiant(Long cinEtudiant);
}
