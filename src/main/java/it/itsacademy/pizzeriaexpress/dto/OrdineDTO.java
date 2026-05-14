package it.itsacademy.pizzeriaexpress.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdineDTO {

    private String codice;
    private Collection<OrdinePizzaDTO> ordini_pizze;
    private RiderDTO riders;
}
