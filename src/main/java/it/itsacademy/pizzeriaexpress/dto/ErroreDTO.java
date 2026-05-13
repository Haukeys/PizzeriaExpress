package it.itsacademy.pizzeriaexpress.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErroreDTO {
    private String timestamp = java.time.LocalDateTime.now().toString();
    private Integer status;
    private String error;
    private String message;


}

