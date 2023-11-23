package tn.esprit.spring.Service;

import tn.esprit.spring.entity.Foyer;
import tn.esprit.spring.entity.TypeC;

import java.util.List;

public interface FoyerServiceImpl {
    Foyer addFoyer(Foyer foyer);
    List<Foyer> getAllFoyers();
    Foyer updateFoyer(Foyer foyer);
    void deleteFoyer(Long id);
    List<Foyer> findFoyersByTypeChambre(TypeC TypeChambre);
    long findCapaciteFoyerByIdBloc(Long Idbloc);
}
