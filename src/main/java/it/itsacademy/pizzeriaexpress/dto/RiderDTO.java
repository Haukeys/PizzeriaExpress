package it.itsacademy.pizzeriaexpress.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RiderDTO {

    private Long idRider;
    @NotBlank(message = "il nome non puo essere vuoto")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$",message = "il nome non deve contenere caratteri speciali,massimo doppio nome")
    private String nome;
}