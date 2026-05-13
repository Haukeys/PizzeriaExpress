package it.itsacademy.pizzeriaexpress.dto;

import it.itsacademy.pizzeriaexpress.entity.Ordine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
    private String Indirizzo;
    private String telefono;
    private List<Ordine> ordini;
}
