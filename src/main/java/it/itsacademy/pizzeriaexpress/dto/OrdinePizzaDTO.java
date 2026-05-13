package it.itsacademy.pizzeriaexpress.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrdinePizzaDTO {

    private Integer quantita;
    private PizzaDTO pizza;
}