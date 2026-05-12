package it.itsacademy.pizzeriaexpress.utility;

import it.itsacademy.pizzeriaexpress.dto.OrdinePizzaDTO;
import it.itsacademy.pizzeriaexpress.entity.OrdinePizza;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { PizzaUtility.class })
public interface OrdinePizzaUtilityTest {

    public OrdinePizza ordinePizzaDTOToOrdinePizza(OrdinePizzaDTO ordinePizzaDTO);

    public OrdinePizzaDTO ordinePizzaToOrdinePizzaDTO(OrdinePizza ordinePizza);

}
