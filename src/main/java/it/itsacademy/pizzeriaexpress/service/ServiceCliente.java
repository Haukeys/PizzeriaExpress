package it.itsacademy.pizzeriaexpress.service;


import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;

import java.util.List;

public interface ServiceCliente {

    public ClienteDTO registrazione(ClienteDTO clienteDTO);
    public ClienteDTO cerca(Long id);
    public ClienteDTO modifica(Long id,ClienteDTO clienteDTO);
    public ClienteDTO cancella(Long id);
    public List<ClienteDTO> tuttiCLienti();
}
