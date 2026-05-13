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
@Table(name="Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String Indirizzo;
    @Column(nullable = false)
    private String telefono;

    @OneToMany(orphanRemoval = true)//non dimenticare che il cliente fa degli ordini
    @JoinColumn(name="fk_ordine")
    @Column(nullable = false)
    private List<Ordine> ordini;
}
