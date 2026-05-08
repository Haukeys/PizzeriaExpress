package it.itsacademy.pizzeriaexpress.dto;

import it.itsacademy.pizzeriaexpress.entity.Cliente;
import it.itsacademy.pizzeriaexpress.entity.OrdinePizza;
import it.itsacademy.pizzeriaexpress.entity.Rider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdineDTO {

    private String codice;
    private List<OrdinePizza> ordini_pizze;
    private List<Cliente> clienti;
    private List<Rider> riders;
}
