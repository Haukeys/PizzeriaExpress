package it.itsacademy.pizzeriaexpress.service;


import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;

import java.util.Collection;


public interface ServiceCliente {

    public ClienteDTO registrazione(ClienteDTO clienteDTO);
    public ClienteDTO cerca(Long id);
    public ClienteDTO modifica(Long id,ClienteDTO clienteDTO);
    public ClienteDTO cancella(Long id);
    public Collection<ClienteDTO> tuttiClienti();
}
