package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames= false, exclude = {"idReservation", "etudiants"})
@JsonIgnoreProperties("etudiants")
public class Reservation  implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idReservation")
    private Integer idReservation;
    private Long cinEtudiant;
    @Temporal (TemporalType.DATE)
    private Date anneeUniversitaire;
    @Column(name = "estValide")
    private boolean estValide;
    @Enumerated(EnumType.STRING)
    private TypeC TypeChambre ;
    @ManyToMany(mappedBy = "reservations")
    private Set<Etudiant> etudiants;
}
