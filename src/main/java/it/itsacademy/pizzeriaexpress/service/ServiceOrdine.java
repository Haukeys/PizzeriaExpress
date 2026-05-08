package it.itsacademy.pizzeriaexpress.service;

import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;

public interface ServiceOrdine {

    public OrdineDTO crea(OrdineDTO ordineDTO);
    public OrdineDTO cerca(String codice);
    public OrdineDTO modifica(String codice,OrdineDTO ordineDTO);
    public OrdineDTO cancella(String Codice);
}
