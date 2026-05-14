package it.itsacademy.pizzeriaexpress.service;


import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.dto.RegistrazioneClienteDTO;

import java.util.Collection;


public interface ServiceCliente {

    public ClienteDTO registrazione(RegistrazioneClienteDTO registrazione);
    public ClienteDTO cerca(Long idCliente);
    public ClienteDTO modifica(Long idCliente,ClienteDTO clienteDTO);
    public ClienteDTO cancella(Long idCliente);
    public Collection<ClienteDTO> tuttiClienti();
}
