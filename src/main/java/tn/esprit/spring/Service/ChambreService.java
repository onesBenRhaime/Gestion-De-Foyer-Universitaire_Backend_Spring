package tn.esprit.spring.Service;

import tn.esprit.spring.Repository.ChambreRepository;
import tn.esprit.spring.entity.Chambre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChambreService implements ChambreServiceImpl {
    @Autowired
    ChambreRepository chambreRepository;
    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }
    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }
    @Override
    public Chambre updateChambres(Chambre chambre) {
        if (chambreRepository.existsById(chambre.getIdChambre())) {
            return chambreRepository.save(chambre);
        }
        return null ;
    }
    @Override
    public void deleteChambre(Long id) {
        chambreRepository.deleteById(id);
    }

    @Override
    public Chambre GetChambreByEtudiant(Long idEtudiant) {
        return chambreRepository.findChambreByEtudiantId(idEtudiant);
    }
}
