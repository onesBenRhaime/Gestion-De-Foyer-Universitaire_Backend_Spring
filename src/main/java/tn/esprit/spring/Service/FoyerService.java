package tn.esprit.spring.Service;

import tn.esprit.spring.Repository.FoyerRepository;
import tn.esprit.spring.entity.Foyer;
import tn.esprit.spring.entity.TypeC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoyerService implements FoyerServiceImpl {
    @Autowired
    FoyerRepository foyerRepository;

    @Override
    public Foyer addFoyer(Foyer foyer) { return foyerRepository.save(foyer); }
    @Override
    public List<Foyer> getAllFoyers() {
        return foyerRepository.findAll();
    }
    @Override
    public Foyer updateFoyer(Foyer foyer) {
        if (foyerRepository.existsById(foyer.getIdFoyer())) {
            return foyerRepository.save(foyer);
        }
        return null;
    }
    @Override
    public void deleteFoyer(Long id) { foyerRepository.deleteById(id); }

    @Override
    public List<Foyer> findFoyersByTypeChambre(TypeC TypeChambre) {
        return foyerRepository.findFoyersByTypeChambre(TypeChambre);
    }

    @Override
    public long findCapaciteFoyerByIdBloc(Long Idbloc) {
        return foyerRepository.findCapaciteFoyerByIdBloc(Idbloc);
    }
}
