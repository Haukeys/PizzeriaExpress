package it.itsacademy.pizzeriaexpress.service;

import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;

public interface ServicePizza {

    public PizzaDTO creaPizza(PizzaDTO pizzaDTO);
    public PizzaDTO modificaPizza(Long id,PizzaDTO pizzaDTO);
    public PizzaDTO cancellaPizza(Long id);
    public PizzaDTO trovaPizza(Long id);
}
