package tn.esprit.spring.Service;


import tn.esprit.spring.Repository.ChambreRepository;
import tn.esprit.spring.Repository.EtudiantRepository;
import tn.esprit.spring.Repository.ReservationRepository;
import tn.esprit.spring.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EtudiantService implements EtudiantServiceImpl{

    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ChambreRepository chambreRepository;
    @Override
    public Etudiant addEtudiant(Etudiant etudiant) {return etudiantRepository.save(etudiant);}
    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }
    @Override
    public Etudiant updateEtudiant(Etudiant etudiant) {
        if (etudiantRepository.existsById(etudiant.getIdEtudiant())) {
            return etudiantRepository.save(etudiant);
        }
        return null ;
    }
    @Override
    public void deleteEtudiant(Long id) { etudiantRepository.deleteById(id); }

    @Override
    public Optional<Etudiant> getByCinEtudiant(Long cin) {
        return etudiantRepository.findByCin(cin);    }
    @Override
    public Map<String, Object> getEtudiantInfoByCIN(Long cin) {
        Map<String, Object> response = new HashMap<>();
        Optional<Etudiant> etudiantopt = etudiantRepository.findByCin(cin);
        if (etudiantopt.isPresent()){
            Etudiant etudiant=etudiantopt.get();
            Reservation reservation =  reservationRepository.findByCinEtudiant(cin);

            Chambre chambre =  chambreRepository.findChambreByEtudiantId(etudiant.getIdEtudiant());
            response.put("chambre", chambre);
            response.put("bloc", chambre.getBloc());
            response.put("foyer", chambre.getBloc().getFoyers());
            response.put("universite", etudiant.getUniversite());
        }
       return response;
    }
}