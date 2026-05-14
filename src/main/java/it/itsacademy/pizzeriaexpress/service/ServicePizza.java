package it.itsacademy.pizzeriaexpress.service;

import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;

import java.util.Collection;


public interface ServicePizza {

    public PizzaDTO creaPizza(PizzaDTO pizzaDTO);
    public PizzaDTO modificaPizza(Long idPizza,PizzaDTO pizzaDTO);
    public PizzaDTO cancellaPizza(Long idPizza);
    public PizzaDTO trovaPizza(Long idPizza);
    public Collection<PizzaDTO> tutteLePizze();
}
