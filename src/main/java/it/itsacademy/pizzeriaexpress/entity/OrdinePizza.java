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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordine_pizza")//allignamento con il db
    private Long id;
    private Integer quantita;

    @ManyToOne
    @JoinColumn(name ="fk_pizza")
    private Pizza pizza;

}
