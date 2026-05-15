package it.itsacademy.pizzeriaexpress.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdinePizzaNascitaDTO {

    private Long idPizza;
    @NotBlank(message ="la quantita non puo essere vuota" )
    @Positive(message = "la quantita deve essere positivo")
    private Integer quantita;

}
