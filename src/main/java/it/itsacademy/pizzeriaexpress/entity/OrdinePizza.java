package it.itsacademy.pizzeriaexpress.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OrdinePizza")
public class OrdinePizza {
    @Id
    private Long id;
    private Integer quantita;

    @ManyToOne
    @JoinColumn(name ="fk_pizza")
    private Pizza pizza;

}
