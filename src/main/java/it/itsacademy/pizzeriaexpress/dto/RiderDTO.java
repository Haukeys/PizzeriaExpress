package it.itsacademy.pizzeriaexpress.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RiderDTO {

    private Long id;
    private String nome;
}