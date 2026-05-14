package it.itsacademy.pizzeriaexpress.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PizzaDTO {

    private Long idPizza;
    private String nome;
    private String descrizione;
    private Double prezzo;
}
