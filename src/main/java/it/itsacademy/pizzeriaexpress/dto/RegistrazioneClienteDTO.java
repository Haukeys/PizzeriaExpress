package it.itsacademy.pizzeriaexpress.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrazioneClienteDTO {

    private Long idCliente;//essendo un dto nuovo necessita di un id pre matchare con un CLiente
    private String nome;
    private String indirizzo;
    private String telefono;

    private Collection<OrdineNascitaClienteDTO> ordini;
}
