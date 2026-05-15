package it.itsacademy.pizzeriaexpress.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PizzaDTO {

    private Long idPizza;
    @NotBlank(message = "il nome non puo essere vuoto")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$",message = "il nome non deve contenere caratteri speciali,massimo doppio nome")
    private String nome;
    @NotBlank(message="la descrizione non puo essere vuota")
    private String descrizione;
    @NotBlank(message ="il prezzo non puo essere vuoto" )
    @Positive(message = "il prezzo deve essere positivo")
    private Double prezzo;
}
