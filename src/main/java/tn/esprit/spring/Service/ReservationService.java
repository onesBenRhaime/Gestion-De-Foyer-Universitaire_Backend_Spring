package tn.esprit.spring.Service;

import tn.esprit.spring.Repository.ChambreRepository;
import tn.esprit.spring.Repository.EtudiantRepository;
import tn.esprit.spring.Repository.ReservationRepository;
import tn.esprit.spring.entity.Chambre;
import tn.esprit.spring.entity.Etudiant;
import tn.esprit.spring.entity.Reservation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReservationService implements ReservationServiceImpl {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    /*************** Add Reservation ************************/
    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    /****************************Get All Reservations*******************/

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    /****************************Update Reservation*******************/
    @Override
    public Reservation updateReservation(Reservation reservation) {
        if (reservationRepository.existsById(reservation.getIdReservation())) {
            return reservationRepository.save(reservation);
        }
        return null;
    }
    /****************************delete Reservation*******************/
    @Override
    public void deleteReservation(Integer id) { reservationRepository.deleteById(id); }


    /***************Add V 3 Advances  ************************/
    @Override
    public Map<String, Object> ajouterReservation(Reservation reservation) {
        System.out.println("*****************ajouterReservation**************");
        Map<String, Object> response = new HashMap<>();
        // Vérifier si l'étudiant avec le CIN existe
        Optional<Etudiant> etudiantOpt = etudiantRepository.findByCin(reservation.getCinEtudiant());
        if (etudiantOpt.isEmpty()) {
            // L'étudiant n'existe pas, renvoyer un message d'erreur
            response.put("message", "Étudiant non trouvé avec le CIN : " + reservation.getCinEtudiant());
            return response;
        }else{

            // L'étudiant existe, continuez avec la réservation
            Etudiant etudiantConnecte = etudiantOpt.get();

            reservation.setEstValide(false);

            // Associer la réservation à l'étudiant connecté
            reservation.setEtudiants(Set.of(etudiantConnecte));

            // Associer l'étudiant connecté à la réservation
            Set<Reservation> reservationsEtudiant = etudiantConnecte.getReservations();
            if (reservationsEtudiant == null) {
                reservationsEtudiant = new HashSet<>();
            }
            reservationsEtudiant.add(reservation);
            etudiantConnecte.setReservations(reservationsEtudiant);

            // Ajouter la réservation
            Reservation nouvelleReservation = reservationRepository.save(reservation);

            response.put("message", "Réservation ajoutée avec succès");
            response.put("reservation", nouvelleReservation);
            return response;
        }
    }

    @Override
    public void nonValide(Integer id) {
       Reservation r= reservationRepository.getReferenceById(id);
         r.setEstValide(false);
        reservationRepository.save(r);
    }

   @Override
   public Map<String, Object> estValide(Integer idReservation){
     Map<String, Object> response = new HashMap<>();

    // Vérifier si la réservation avec l'ID spécifié existe
    Optional<Reservation> reservationOpt = reservationRepository.findById(idReservation);
    if (reservationOpt.isEmpty()) {
        response.put("message", "Aucune réservation trouvée avec l'ID : " + idReservation);
        return response;
    }
    // La réservation existe, continuer le processus de validation
    Reservation reservation = reservationOpt.get();

    // Récupérer une chambre disponible en fonction de certains critères
    Chambre chambreDisponible = chambreRepository.findChambreDisponible(reservation.getTypeChambre());

    if (chambreDisponible != null) {

        // Vérifier si la réservation a déjà une chambre attribuée
        if (chambreDisponible.getReservations() != null) {
            response.put("message", "La réservation a déjà une chambre attribuée.");
            return response;
        }

        // Générer le numéro de réservation
        String numReservation = genererNumeroReservation(reservation);
        System.out.println("numReservation    : " + numReservation);
        reservation.setNumReservation(numReservation);

        // Mise à jour de la capacité de la chambre
        chambreDisponible.setCapaciteChambre(chambreDisponible.getCapaciteChambre() - 1);

       // Si une chambre du type demandé existe, valider la réservation
        reservation.setEstValide(true);

        // Ajouter la relation entre la chambre et la réservation
        chambreDisponible.getReservations().add(reservation);
        reservation.setTypeChambre(chambreDisponible.getTypeChambre());

        // Enregistrer les modifications
        chambreRepository.save(chambreDisponible);
        reservationRepository.save(reservation);
        response.put("message", "Réservation validée avec succès.");

    } else {
        response.put("message", "La réservation ne peut pas être ajoutée. Aucune chambre disponible.");
    }

    return response;
}

    private String genererNumeroReservation(Reservation reservation) {
        Chambre chambre =  chambreRepository.findChambreDisponible(reservation.getTypeChambre());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String anneeUniversitaire = sdf.format(new Date());

        return chambre.getNumeroChambre() + "-" + chambre.getBloc().getNomBloc() + "-" + anneeUniversitaire;
    }


}
