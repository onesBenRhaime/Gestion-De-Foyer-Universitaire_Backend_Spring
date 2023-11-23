package tn.esprit.spring.Service;


import tn.esprit.spring.Repository.EtudiantRepository;
import tn.esprit.spring.entity.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService implements EtudiantServiceImpl{

    @Autowired
    EtudiantRepository etudiantRepository;


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
}