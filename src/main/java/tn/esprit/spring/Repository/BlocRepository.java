package tn.esprit.spring.Repository;

import tn.esprit.spring.entity.Bloc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {
List<Bloc> findByCapaciteBlocLike(Long capaciteBloc);
}
