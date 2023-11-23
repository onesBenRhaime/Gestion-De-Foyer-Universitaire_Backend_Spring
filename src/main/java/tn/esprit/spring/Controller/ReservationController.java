package tn.esprit.spring.Controller;

import tn.esprit.spring.Service.ReservationServiceImpl;
import tn.esprit.spring.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservation")
@CrossOrigin(origins = "http://localhost:4200/")
public class ReservationController {
    @Autowired
    ReservationServiceImpl reservationService;

    @PostMapping("/addReservation")
    Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }
    @PostMapping("/ajouterReservation")
    Map<String, Object> ajouterReservation(@RequestBody Reservation reservation) {
        return reservationService.ajouterReservation(reservation);
    }

    @PutMapping("/updateReservation")
    Reservation updateReservation(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }
    /*@PutMapping("/estValide/{id}")
    void estValide(@PathVariable String id) {
        reservationService.estValide(id);
    }*/

    /************** Valider une reservation ou refuser *************/

    @PutMapping("/estValide/{id}")
    public ResponseEntity<Map<String, Object>> estValide(@PathVariable Integer id) {
        Map<String, Object> response = reservationService.estValide(id);

        if ("Succès".equals(response.get("status"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    /**************************************************************/

    @PutMapping("/ouiValide/{id}")
    void ouiValide(@PathVariable Integer id) {
        reservationService.ouiValide(id);
    }

    @PutMapping("/nonValide/{id}")
    void nonValide(@PathVariable Integer id) {
        reservationService.nonValide(id);
    }

    @GetMapping("/getAllReservations")
    List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @DeleteMapping("/deleteReservation/{id}")
    void deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
    }
}
