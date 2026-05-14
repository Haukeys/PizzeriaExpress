package it.itsacademy.pizzeriaexpress.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Pizza")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPizza;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descrizione;
    @Column(nullable = false)
    private Double prezzo;
}
