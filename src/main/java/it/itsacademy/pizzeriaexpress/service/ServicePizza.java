package it.itsacademy.pizzeriaexpress.service;

import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;

import java.util.Collection;


public interface ServicePizza {

    public PizzaDTO creaPizza(PizzaDTO pizzaDTO);
    public PizzaDTO modificaPizza(Long id,PizzaDTO pizzaDTO);
    public PizzaDTO cancellaPizza(Long id);
    public PizzaDTO trovaPizza(Long id);
    public Collection<PizzaDTO> tutteLePizze();
}
