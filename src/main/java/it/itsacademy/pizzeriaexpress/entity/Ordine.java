package it.itsacademy.pizzeriaexpress.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
    @JoinColumn(name="fk_ordine_pizza")
    @Column(nullable = false)
    private List<OrdinePizza> ordini_pizze;

    @OneToMany
    @JoinColumn(name="fk_cliente")
    @Column(nullable = false)
    private List<Cliente> clienti;

    @ManyToOne
    @JoinColumn(name="fk_rider")
    private List<Rider> riders;



}
