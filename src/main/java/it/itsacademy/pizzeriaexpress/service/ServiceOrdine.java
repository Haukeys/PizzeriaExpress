package it.itsacademy.pizzeriaexpress.service;

import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;
import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;


import java.util.Collection;

public interface ServiceOrdine {
//da non dimenticare che l ordine e composto da cliente e di pizza

    public OrdineDTO creaOrdine(Long idOrdine,OrdineDTO ordineDTO);
    public OrdineDTO cercaOrdine(Long idOrdine, String codice);
    public OrdineDTO modificaOrdine(Long idOrdine,String codice,OrdineDTO ordineDTO);
    public OrdineDTO cancellaOrdine(Long idOrdine,String Codice);
    public Collection<OrdineDTO> tuttiOrdini();
    public OrdineDTO aggiungiPizza(Long idOrdine, String codice, PizzaDTO pizza, Integer quantita);
}
