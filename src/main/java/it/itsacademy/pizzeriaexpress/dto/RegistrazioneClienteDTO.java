package it.itsacademy.pizzeriaexpress.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrazioneClienteDTO {

    private Long idCliente;//essendo un dto nuovo necessita di un id pre matchare con un CLiente
    @NotBlank(message = "il nome non puo essere vuoto")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$",message = "il nome non deve contenere caratteri speciali,massimo doppio nome")
    private String nome;
    @NotBlank(message="l'indirizzo non deve essere vuoto")
    private String indirizzo;
    @NotBlank(message = "il numero di telefono non puo essere vuoto")
    @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message = "il numero di telefono deve rispettare lo standard internazionale E.164")
    private String telefono;
    @NotBlank(message = "la lista di ordini non puo essere vuota")
    private Collection<OrdineNascitaClienteDTO> ordini;
}
