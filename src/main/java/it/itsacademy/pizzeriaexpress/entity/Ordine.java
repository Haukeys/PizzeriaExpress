package it.itsacademy.pizzeriaexpress.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_ordine",discriminatorType =  DiscriminatorType.STRING)
@Table(name="Ordine")

public class Ordine {

    @Id
    private String codice;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE},orphanRemoval = true)//Merge car il ne fait pas l auto incrementation car ici
    @JoinColumn(name="fk_ordine_pizza")
    @Column(nullable = false)
    private Collection<OrdinePizza> ordini_pizze;

    @ManyToOne
    @JoinColumn(name="fk_rider")
    private Rider rider;


}
