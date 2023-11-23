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

import java.util.*;

@Service
public class ReservationService implements ReservationServiceImpl {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    @Override
     public  Map<String, Object> ajouterReservation(Reservation reservation){
      Map<String, Object> result = new HashMap<>();

      // Vérifier si l'étudiant avec le CIN existe
      Optional<Etudiant> etudiantOpt = etudiantRepository.findByCin(reservation.getCinEtudiant());
      if (etudiantOpt.isEmpty()) {
          // L'étudiant n'existe pas, renvoyer un message d'erreur
          result.put("success", false);
          result.put("message", "Étudiant non trouvé avec le CIN : " + reservation.getCinEtudiant());
          return result;
      }

      // L'étudiant existe, continuez avec la réservation
      Etudiant etudiantConnecte = etudiantOpt.get();

      // Valider ou ajuster d'autres champs de la réservation si nécessaire
      reservation.setEstValide(false); // Par exemple, initialement non validée


      // Associer la réservation à l'étudiant connecté
      reservation.setEtudiants(Set.of(etudiantConnecte));

      // Associer l'étudiant connecté à la réservation
      Set<Reservation> reservationsEtudiant = etudiantConnecte.getReservations();
      if (reservationsEtudiant == null) {
          reservationsEtudiant = new HashSet<>();
      }
      reservationsEtudiant.add(reservation);
      etudiantConnecte.setReservations(reservationsEtudiant);

      // Enregistrer la réservation et l'étudiant
      reservationRepository.save(reservation);

      // Renvoyer un message de succès
      result.put("success", true);
      result.put("message", "Réservation ajoutée avec succès pour l'étudiant avec le CIN : " + reservation.getCinEtudiant());
      result.put("reservation", reservation); // Vous pouvez ajouter la réservation dans le résultat si nécessaire

      return result;

  }
    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        if (reservationRepository.existsById(reservation.getIdReservation())) {
            return reservationRepository.save(reservation);
        }
        return null;
    }
    @Override
    public void deleteReservation(Integer id) { reservationRepository.deleteById(id); }

    @Override
    public void nonValide(Integer id) {
       Reservation r= reservationRepository.getReferenceById(id);
         r.setEstValide(false);
        reservationRepository.save(r);
    }
    @Override
    public void ouiValide(Integer idReservation) {
       /* Reservation r= reservationRepository.getReferenceById(idReservation);
        r.setEstValide(true);
        reservationRepository.save(r);*/
        float id=0;
        System.out.println("onessssssssssssssss" +id);
        System.out.println("Récupérer la réservation par son ID");
            // Récupérer la réservation par son ID
            Reservation reservation = reservationRepository.findById(idReservation)
                    .orElseThrow(() -> new EntityNotFoundException("Réservation non trouvée avec l'ID : " + idReservation));
              System.out.println("reservation : **********"+reservation);
            // Vérifier si une chambre du type demandé existe
            Optional<Chambre> chambreOpt = chambreRepository.findAvailableChambreByType(reservation.getTypeChambre());
              System.out.println("chambreOpt  **********"+chambreOpt);
            if (chambreOpt.isPresent()) {
                // Si une chambre du type demandé existe, valider la réservation
                Chambre chambre = chambreOpt.get();
                reservation.setEstValide(true);

                // Ajouter la relation entre la chambre et la réservation
                chambre.getReservations().add(reservation);

                // Enregistrer les modifications
                reservationRepository.save(reservation);
            } else {
                // Si aucune chambre du type demandé n'est disponible, vous pouvez gérer cela en conséquence (par exemple, lancer une exception ou traiter différemment).
                throw new EntityNotFoundException("Aucune chambre disponible du type demandé pour la réservation avec l'ID : " + idReservation);
            }
        }

@Override
public Map<String, Object> estValide(Integer idReservation){

    Map<String, Object> response = new HashMap<>();

    // Vérifier si la réservation avec l'ID spécifié existe
    Optional<Reservation> reservationOpt = reservationRepository.findById(idReservation);
    if (reservationOpt.isEmpty()) {
        response.put("status", "Échec");
        response.put("message", "Aucune réservation trouvée avec l'ID : " + idReservation);
        return response;
    }

    // La réservation existe, continuer le processus de validation
    Reservation reservation = reservationOpt.get();

    // Vérifier si une chambre du type demandé existe
    Optional<Chambre> chambreOpt = chambreRepository.findAvailableChambreByType(reservation.getTypeChambre());

    if (chambreOpt.isPresent()) {
        // Si une chambre du type demandé existe, valider la réservation
        Chambre chambre = chambreOpt.get();
        reservation.setEstValide(true);

        // Ajouter la relation entre la chambre et la réservation
        chambre.getReservations().add(reservation);
        reservation.setTypeChambre(chambre.getTypeChambre());

        // Enregistrer les modifications
        chambreRepository.save(chambre);
        reservationRepository.save(reservation);

        response.put("status", "Succès");
        response.put("message", "Réservation validée avec succès.");
    } else {
        // Si aucune chambre du type demandé n'est disponible
        response.put("status", "Échec");
        response.put("message", "Aucune chambre disponible du type demandé pour la réservation.");
    }

    return response;
}

}
