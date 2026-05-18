package it.itsacademy.pizzeriaexpress.service;

import it.itsacademy.pizzeriaexpress.dto.*;

import java.util.Collection;

public interface ServiceOrdine {
//da non dimenticare che l ordine e composto da cliente e di pizza

    //saveAndFull per sync l inserimento/creazione del ordine con la creazione del cliente
    public OrdineDTO creaOrdine(Long idCliente,OrdineNascitaClienteDTO ordineNacistaDTO);
    public OrdinePrioritarioDTO creaOrdinePrioritario(Long idCliente,OrdinePrioritarioNascitaClienteDTO ordinePrioritarioNascitaClienteDTO);
    public OrdineDTO cercaOrdine(Long idCliente ,String codice);
    public OrdineDTO modificaOrdine(Long idCliente, String codice, OrdineDTO ordineDTO) ;
    public OrdineDTO cancellaOrdine(Long idCliente,String Codice);
    public Collection<OrdineDTO> tuttiOrdini();
    public Collection<OrdinePrioritarioDTO> tuttiOrdiniPrioritari();
    public OrdineDTO aggiungiPizza(Long idCliente, String codice, PizzaDTO pizza, Integer quantita);
}
