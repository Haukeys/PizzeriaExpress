package it.itsacademy.pizzeriaexpress.controller;


import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;
import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;
import it.itsacademy.pizzeriaexpress.service.ServiceOrdine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ordini")
public class OrdineController {

    @Autowired
    private ServiceOrdine serviceOrdine;

    @PostMapping(path="/{idCliente}", consumes = "application/json", produces = "application/json")
    public OrdineDTO createOrdine(@PathVariable Long idCliente, @RequestBody OrdineDTO ordineDTO) {
        return serviceOrdine.creaOrdine(idCliente, ordineDTO);
    }

    @GetMapping(path="/{idCliente}/{codice}", produces = "application/json")
    public OrdineDTO cercaOrdine(@PathVariable Long idCliente,String codice) {
        return serviceOrdine.cercaOrdine(idCliente, codice);
    }

    @PutMapping(path="/{idCliente}/{codice}", consumes = "application/json", produces = "application/json")
    public OrdineDTO modificaOrdine(@PathVariable Long idCliente, String codice, @RequestBody OrdineDTO ordineDTO) {
        return serviceOrdine.modificaOrdine(idCliente, codice, ordineDTO);
    }

    @DeleteMapping(path="/{idCliente}/{codice}", produces = "application/json")
    public OrdineDTO eliminaOrdine(@PathVariable Long idCliente, String codice) {
        return serviceOrdine.cancellaOrdine(idCliente, codice);
    }

    @GetMapping(produces = "application/json")
    public Collection<OrdineDTO> tuttiGliOrdini() {
        return serviceOrdine.tuttiOrdini();
    }

    @PostMapping(path="/{idCliente}/{codice}/pizze", consumes = "application/json", produces = "application/json")
    public OrdineDTO aggiungiPizza(@PathVariable Long idCliente, String codice, PizzaDTO pizza,Integer quantita) {
        return serviceOrdine.aggiungiPizza(idCliente, codice, pizza, quantita);
    }
}
