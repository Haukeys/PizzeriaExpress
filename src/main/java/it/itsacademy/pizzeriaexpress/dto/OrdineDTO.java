package it.itsacademy.pizzeriaexpress.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdineDTO {

    @NotBlank(message = "il codice non puo essere vuoto")
    @Pattern(regexp = "^ORD[1-9]\\d*$")
    private String codice;
    @NotBlank(message="l'ordine non puo essere vuoto")
    @NotNull(message="l'ordine non puo essere null")
    private Collection<OrdinePizzaDTO> ordini_pizze;

    private RiderDTO rider;
}
