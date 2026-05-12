package it.itsacademy.pizzeriaexpress.utility;

import it.itsacademy.pizzeriaexpress.dto.OrdinePizzaDTO;
import it.itsacademy.pizzeriaexpress.entity.OrdinePizza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { PizzaUtility.class })//car utiliser dans cette classe {} facilite l autocreation/complilation
public interface OrdinePizzaUtility {
    @Mapping(target = "id", ignore = true)
    public OrdinePizza ordinePizzaDTOToOrdinePizza(OrdinePizzaDTO ordinePizzaDTO);

    public OrdinePizzaDTO ordinePizzaToOrdinePizzaDTO(OrdinePizza ordinePizza);

}

