package tn.esprit.spring.Service;

import tn.esprit.spring.entity.Universite;

import java.util.List;

public interface UniversiteServiceImpl {
    Universite addUniversite(Universite universite);
    List<Universite> getAllUniversites();
    Universite updateUniversite(Universite universite);
    void deleteUniversite(Long id);

    Universite findUniversitesByChambreId(Long chambreId);

    Universite findUniversitesByIdEtudiant(Long IdEtudiant);


}
