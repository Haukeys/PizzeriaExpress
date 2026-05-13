package it.itsacademy.pizzeriaexpress.service;

import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;
import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;
import java.util.Collection;

public interface ServiceOrdine {
//da non dimenticare che l ordine e composto da cliente e di pizza

    public OrdineDTO crea(Long id,OrdineDTO ordineDTO);
    public OrdineDTO cerca(Long id, String codice);
    public OrdineDTO modifica(Long id,String codice,OrdineDTO ordineDTO);
    public OrdineDTO cancella(Long id,String Codice);
    public Collection<OrdineDTO> tuttiOrdini();
    public OrdineDTO aggiungiPizza(Long id,String codice,PizzaDTO pizzaDTO,Integer quantita);
}
