package it.itsacademy.pizzeriaexpress.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long idCliente;
    private String nome;
    private String Indirizzo;
    private String telefono;
    private List<OrdineDTO> ordini;
}
