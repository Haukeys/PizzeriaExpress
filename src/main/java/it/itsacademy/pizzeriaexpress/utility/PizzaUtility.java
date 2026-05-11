package it.itsacademy.pizzeriaexpress.utility;

import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;
import it.itsacademy.pizzeriaexpress.entity.Pizza;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel="spring")
public interface PizzaUtility {

    public Pizza pizzaDTOToPizza(PizzaDTO pizzaDTO);
    public PizzaDTO pizzaToPizzaDTO(Pizza pizza);
    public Collection<PizzaDTO> tutteLePizze(Collection<Pizza> pizze);
}
