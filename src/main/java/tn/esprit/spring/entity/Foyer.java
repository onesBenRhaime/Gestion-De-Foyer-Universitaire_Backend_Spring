package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="foyer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude={"idFoyer"},includeFieldNames= false)
public class Foyer implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idFoyer")
    private Long idFoyer;
    private String nomFoyer ;
    private Long capaciteFoyer;
    @OneToOne
    private Universite universite;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy="foyers")
    private Set<Bloc> blocs ;

}
