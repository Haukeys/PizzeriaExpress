package it.itsacademy.pizzeriaexpress.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdineNascitaClienteDTO {

    private String codice;
    private Collection<OrdinePizzaNascitaDTO> ordini_pizze;
    private Long idRider;//per evitare nullpointexception in caso di errore nel codice "reference"
}
