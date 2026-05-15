package it.itsacademy.pizzeriaexpress.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrdinePizzaDTO {

    private Integer quantita;
    @NotBlank(message = "la pizza deve esistere una pizza")
    private PizzaDTO pizza;
}