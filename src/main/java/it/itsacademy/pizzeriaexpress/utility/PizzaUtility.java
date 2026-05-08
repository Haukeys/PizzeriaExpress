package it.itsacademy.pizzeriaexpress.utility;

import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;
import it.itsacademy.pizzeriaexpress.entity.Pizza;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface PizzaUtility {

    public Pizza pizzaDTOToPizza(PizzaDTO pizzaDTO);
    public PizzaDTO pizzaToPizzaDTO(Pizza pizza);

}
