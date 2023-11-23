package tn.esprit.spring.Service;


import tn.esprit.spring.entity.Etudiant;

import java.util.List;

public interface EtudiantServiceImpl {
    Etudiant addEtudiant(Etudiant etudiant);
    List<Etudiant> getAllEtudiants();
    Etudiant updateEtudiant(Etudiant etudiant);
    void deleteEtudiant(Long id);
}
