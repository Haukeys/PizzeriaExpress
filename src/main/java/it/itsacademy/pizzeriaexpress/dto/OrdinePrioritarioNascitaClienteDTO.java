package it.itsacademy.pizzeriaexpress.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrdinePrioritarioNascitaClienteDTO extends OrdineNascitaClienteDTO {
    @NotBlank(message="sovrapprezzo non puo essere vuoto")
    @NotNull(message = "sovrapprezzo non puo essere null")
    private Double sovrapprezzo;
}
