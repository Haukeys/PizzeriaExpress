package it.itsacademy.pizzeriaexpress.service;

import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;
import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;


import java.util.Collection;

public interface ServiceOrdine {
//da non dimenticare che l ordine e composto da cliente e di pizza

    public OrdineDTO creaOrdine(Long id,OrdineDTO ordineDTO);
    public OrdineDTO cercaOrdine(Long id, String codice);
    public OrdineDTO modificaOrdine(Long id,String codice,OrdineDTO ordineDTO);
    public OrdineDTO cancellaOrdine(Long id,String Codice);
    public Collection<OrdineDTO> tuttiOrdini();
    public OrdineDTO aggiungiPizza(Long id, String codice, PizzaDTO pizza, Integer quantita);
}
