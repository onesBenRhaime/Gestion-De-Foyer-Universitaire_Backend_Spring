package tn.esprit.spring.Repository;

import tn.esprit.spring.entity.Foyer;
import tn.esprit.spring.entity.TypeC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoyerRepository extends JpaRepository<Foyer, Long> {

    //Get list foyer qui a un typeChambre en parametre
    @Query("SELECT f FROM Foyer f JOIN Chambre c on (c.bloc.foyers.idFoyer = f.idFoyer) WHERE c.TypeChambre = :TypeChambre")
    List<Foyer> findFoyersByTypeChambre(@Param("TypeChambre") TypeC TypeChambre);

    //Get CapaciteFoyer  qui a un blocId en parametre
    @Query("SELECT f.capaciteFoyer FROM Foyer f JOIN f.blocs b WHERE b.idBloc = :Idbloc")
    long findCapaciteFoyerByIdBloc(@Param("Idbloc") Long Idbloc);
 
}
