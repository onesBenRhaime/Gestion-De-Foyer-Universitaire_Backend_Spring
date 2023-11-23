package tn.esprit.spring.Repository;

import tn.esprit.spring.entity.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UniversiteRepository extends JpaRepository<Universite, Long> {

    /*
    @Query("SELECT u FROM Universite u JOIN u.foyer f JOIN f.blocs b JOIN b.chambres c WHERE c.idChambre = :chambreId")
    Universite findUniversitesByChambreId(@Param("chambreId") Long chambreId);
    */


    @Query("SELECT u FROM Universite u JOIN Chambre c on (c.bloc.foyers.universite.idUniversite = u.idUniversite) WHERE c.idChambre = :chambreId")
    Universite findUniversitesByChambreId(@Param("chambreId") Long chambreId);


  //  Universite findByFoyerBlocsChambresIdChambre(Long chambreId);

    /*
    @Query("select * from universite u , foyer f ,bloc b, chambre c where u.id_universite=f.universite_id_universite and f.id_foyer = b.foyers_id_foyer and c.bloc_id_bloc = b.id_bloc and c.id_chambre= :chambreId ")
    List<Universite> findUniversitesByChambreId(Long chambreId);

findByFoyerblocschambresIdChambres
*/

    //getUniversiteByIdEtudiant
    @Query("SELECT u FROM Universite u JOIN u.foyer f JOIN f.blocs b JOIN b.chambres c JOIN c.Reservations r JOIN r.etudiants e " +
            "WHERE e.idEtudiant = :IdEtudiant")
    Universite findUniversitesByIdEtudiant(@Param("IdEtudiant") Long IdEtudiant);

}
