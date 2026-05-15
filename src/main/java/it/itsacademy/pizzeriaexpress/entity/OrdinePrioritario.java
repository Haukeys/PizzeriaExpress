package it.itsacademy.pizzeriaexpress.entity;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("prioritario")
public class OrdinePrioritario extends Ordine {

    @Column(nullable = true)//dipende se l ordine ha un rider
    private Double sovrapprezzo;

}
