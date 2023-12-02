package tn.esprit.spring.Service;


import tn.esprit.spring.entity.Etudiant;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EtudiantServiceImpl {
    Etudiant addEtudiant(Etudiant etudiant);
    List<Etudiant> getAllEtudiants();
    Etudiant updateEtudiant(Etudiant etudiant);
    void deleteEtudiant(Long id);

    Optional<Etudiant> getByCinEtudiant(Long cin);


    Map<String, Object> getEtudiantInfoByCIN(Long cin);
}
