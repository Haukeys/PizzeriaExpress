package it.itsacademy.pizzeriaexpress.controller;


import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;
import it.itsacademy.pizzeriaexpress.entity.Pizza;
import it.itsacademy.pizzeriaexpress.service.ServicePizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/pizze")
public class PizzaController {

    @Autowired
    private ServicePizza servicePizza;

    @PostMapping(consumes ="application/json ", produces = "application/json ")
    public PizzaDTO createPizza(@RequestBody PizzaDTO pizzaDTO) {
        return servicePizza.creaPizza(pizzaDTO);
    }
    @GetMapping(path="/{id}", produces = "application/json")
    public PizzaDTO cercaPizza(@PathVariable Long idPizza) {
        return  servicePizza.trovaPizza(idPizza);
    }
    @GetMapping(produces = "application/json")
    public Collection<PizzaDTO> menu() {
        return  servicePizza.tutteLePizze();
    }
    @DeleteMapping(path="/{id}", produces = "application/json")
    public PizzaDTO eliminaPizza(@PathVariable Long idPizza) {
        return  servicePizza.cancellaPizza(idPizza);
    }

}
