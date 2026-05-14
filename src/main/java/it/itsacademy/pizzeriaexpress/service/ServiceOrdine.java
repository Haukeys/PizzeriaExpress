package it.itsacademy.pizzeriaexpress.service;

import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;
import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;
import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;

import java.util.Collection;

public interface ServiceOrdine {
//da non dimenticare che l ordine e composto da cliente e di pizza

    public OrdineDTO creaOrdine(Long idCliente,OrdineDTO ordineDTO);
    public OrdineDTO cercaOrdine(Long idCliente, String codice);
    public OrdineDTO modificaOrdine(Long idCliente,String codice,OrdineDTO ordineDTO);
    public OrdineDTO cancellaOrdine(Long idCliente,String Codice);
    public Collection<OrdineDTO> tuttiOrdini();
    public OrdineDTO aggiungiPizza(Long idCliente, String codice, PizzaDTO pizza, Integer quantita);
}
