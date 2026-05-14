package it.itsacademy.pizzeriaexpress.service;


import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.dto.RegistrazioneClienteDTO;
import it.itsacademy.pizzeriaexpress.entity.*;
import it.itsacademy.pizzeriaexpress.exception.NotFoundException;
import it.itsacademy.pizzeriaexpress.repository.ClienteRepository;
import it.itsacademy.pizzeriaexpress.repository.PizzaRepository;
import it.itsacademy.pizzeriaexpress.repository.RiderRepository;
import it.itsacademy.pizzeriaexpress.utility.ClienteUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;


@Service
@Transactional
public class ServiceClienteImpl implements ServiceCliente {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteUtility utilCliente;

    @Autowired
    private PizzaRepository pizzaRepository; // Pour vérifier les pizzas existantes

    @Autowired
    private RiderRepository riderRepository; // Pour vérifier le rider existant

    @Override
    public ClienteDTO registrazione(RegistrazioneClienteDTO registrazioneDTO) {
    return null;
    }

    @Override
    public ClienteDTO cerca (Long idCliente) {
        Cliente target = clienteRepository.findById(idCliente).orElseThrow(
                ()-> new NotFoundException("cliente non trovato o inesistente"));
        return utilCliente.clienteToClienteDTO(target);

    }


    @Override
    public ClienteDTO modifica(Long id, ClienteDTO clienteDTO) {
        clienteDTO.setId(idCliente);
        Cliente saved = clienteRepository.save(utilCliente.clienteDTOToCliente(clienteDTO));
        return utilCliente.clienteToClienteDTO(saved);


    }

    @Override
    public ClienteDTO cancella(Long idCliente) {
        ClienteDTO target = cerca(idCliente);
        clienteRepository.deleteById(idCliente);
        return target;
    }

    @Override
    public Collection<ClienteDTO> tuttiClienti() {
        return utilCliente.tuttiClienti(clienteRepository.findAll());
    }
}



