package it.itsacademy.pizzeriaexpress.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdinePrioritarioDTO extends OrdineDTO {

    private Double sovrapprezzo;

}
