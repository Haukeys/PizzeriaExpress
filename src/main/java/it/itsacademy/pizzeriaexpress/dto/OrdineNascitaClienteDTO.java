package it.itsacademy.pizzeriaexpress.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdineNascitaClienteDTO {

    @NotBlank(message = "il codice non puo essere vuoto")
    @Pattern(regexp = "^ORD[1-9]\\d*$")
    private String codice;
    @NotBlank(message="la collection ")
    private Collection<OrdinePizzaNascitaDTO> ordini_pizze;
    @NotBlank @Positive(message="l id deve essere positivo")
    private Long idRider;//per evitare nullpointexception in caso di errore nel codice "reference"
}
