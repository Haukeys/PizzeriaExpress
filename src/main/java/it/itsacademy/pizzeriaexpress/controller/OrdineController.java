package it.itsacademy.pizzeriaexpress.controller;


import it.itsacademy.pizzeriaexpress.dto.*;
import it.itsacademy.pizzeriaexpress.service.ServiceOrdine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ordini")
public class OrdineController {

    @Autowired
    private ServiceOrdine serviceOrdine;

    @PostMapping(path="/{idCliente}/simplice", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrdineDTO createOrdine(@PathVariable Long idCliente, @RequestBody OrdineNascitaClienteDTO ordineDTO) {
        return serviceOrdine.creaOrdine(idCliente, ordineDTO);
    }

    @PostMapping(path="/{idCliente}/prioritario", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    public OrdineDTO createOrdinePrioritario(@PathVariable Long idCliente, @RequestBody OrdinePrioritarioNascitaClienteDTO ordinePrioritarioDTO) {
        return serviceOrdine.creaOrdine(idCliente, ordinePrioritarioDTO);
    }

    @GetMapping(path="/{idCliente}/{codice}", produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    public OrdineDTO cercaOrdine(@PathVariable Long idCliente,String codice) {
        return serviceOrdine.cercaOrdine(idCliente, codice);
    }

    @PutMapping(path="/{idCliente}/{codice}", consumes = "application/json", produces = "application/json")
    public OrdineDTO modificaOrdine(@PathVariable Long idCliente, String codice, @RequestBody OrdineDTO ordineDTO) {
        return serviceOrdine.modificaOrdine(idCliente, codice, ordineDTO);
    }

    @DeleteMapping(path="/{idCliente}/{codice}", produces = "application/json")
    @ResponseStatus(HttpStatus.GONE)
    public OrdineDTO eliminaOrdine(@PathVariable Long idCliente, String codice) {
        return serviceOrdine.cancellaOrdine(idCliente, codice);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    public Collection<OrdineDTO> tuttiGliOrdini() {
        return serviceOrdine.tuttiOrdini();
    }

    @GetMapping(path="/tuttiOrdiniPrioritari", produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    public Collection<OrdinePrioritarioDTO> tuttiGliOrdiniPrioritari() {return  serviceOrdine.tuttiOrdiniPrioritari();}

    @GetMapping(path="/tuttiOrdiniNonPrioritari", produces ="application/json" )
    @ResponseStatus(HttpStatus.FOUND)
    public Collection<OrdineDTO> tuttiGliOrdiniNonPrioritari() {return serviceOrdine.tuttiOrdiniNonPrioritari();}

    @PostMapping(path="/{idCliente}/{codice}/pizze", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public OrdineDTO aggiungiPizza(@PathVariable Long idCliente, String codice, PizzaDTO pizza,Integer quantita) {
        return serviceOrdine.aggiungiPizza(idCliente, codice, pizza, quantita);
    }
}
