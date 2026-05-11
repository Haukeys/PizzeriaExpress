package it.itsacademy.pizzeriaexpress.service;


import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.entity.Cliente;
import it.itsacademy.pizzeriaexpress.exception.NotFoundException;
import it.itsacademy.pizzeriaexpress.repository.ClienteRepository;
import it.itsacademy.pizzeriaexpress.utility.ClienteUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceClienteImpl implements ServiceCliente {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteUtility utilCliente;


    @Override
    public ClienteDTO registrazione(ClienteDTO clienteDTO) {
        Cliente saved = clienteRepository.save(utilCliente.clienteDTOToCliente(clienteDTO));
        return utilCliente.clienteToClienteDTO(saved);
    }

    @Override
    public ClienteDTO cerca (Long id) {
        Cliente target = clienteRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("cliente non trovato o inesistente"));
        return utilCliente.clienteToClienteDTO(target);
    }

    @Override
    public ClienteDTO modifica(Long id, ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente saved = clienteRepository.save(utilCliente.clienteDTOToCliente(clienteDTO));
        return utilCliente.clienteToClienteDTO(saved);


    }

    @Override
    public ClienteDTO cancella(Long id) {
        ClienteDTO target = cerca(id);
        clienteRepository.deleteById(id);
        return target;
    }

    @Override
    public Collection<ClienteDTO> tuttiClienti() {
        return utilCliente.tuttiClienti(clienteRepository.findAll());
    }

}
