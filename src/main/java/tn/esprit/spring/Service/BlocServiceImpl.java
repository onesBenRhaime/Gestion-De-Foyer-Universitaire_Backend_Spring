package tn.esprit.spring.Service;

import tn.esprit.spring.entity.Bloc;

import java.util.List;

public interface BlocServiceImpl {
    Bloc addBloc(Bloc bloc);
    List<Bloc> getBlocByCapacite(long capacite);
    List<Bloc> getAllBlocs();
    Bloc updateBloc(Bloc bloc);
    void deleteBloc(Long id);
    void afficherListeBlocs ();
}
