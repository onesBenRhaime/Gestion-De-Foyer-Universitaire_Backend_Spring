package tn.esprit.spring.Service;

import tn.esprit.spring.Repository.UniversiteRepository;
import tn.esprit.spring.entity.Universite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UniversiteService implements UniversiteServiceImpl {
    @Autowired
    UniversiteRepository universiteRepository;
    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }
    @Override
    public List<Universite> getAllUniversites() { return universiteRepository.findAll();}
    @Override
    public Universite updateUniversite(Universite universite) {
        if (universiteRepository.existsById(universite.getIdUniversite())) {
            return universiteRepository.save(universite);
        }
        return null;
    }
    @Override
    public void deleteUniversite(Long id) { universiteRepository.deleteById(id); }

    @Override
    public Universite findUniversitesByChambreId(Long chambreId) {
        return universiteRepository.findUniversitesByChambreId(chambreId);

    }
    @Override
    public Universite findUniversitesByIdEtudiant(Long IdEtudiant) {
        return universiteRepository.findUniversitesByIdEtudiant(IdEtudiant);
    }

}
