package it.itsacademy.pizzeriaexpress.service;


import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.dto.OrdineNascitaClienteDTO;
import it.itsacademy.pizzeriaexpress.dto.OrdinePizzaNascitaDTO;
import it.itsacademy.pizzeriaexpress.dto.RegistrazioneClienteDTO;
import it.itsacademy.pizzeriaexpress.entity.*;
import it.itsacademy.pizzeriaexpress.exception.BadRequestException;
import it.itsacademy.pizzeriaexpress.exception.NotFoundException;
import it.itsacademy.pizzeriaexpress.repository.ClienteRepository;
import it.itsacademy.pizzeriaexpress.repository.PizzaRepository;
import it.itsacademy.pizzeriaexpress.repository.RiderRepository;
import it.itsacademy.pizzeriaexpress.utility.ClienteUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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

    @Autowired
    private ServiceOrdine serviceOrdine;


    @Override
    public ClienteDTO registrazione(RegistrazioneClienteDTO registrazioneCliente) {

        if (registrazioneCliente.getIdCliente() == null || registrazioneCliente.getOrdini().isEmpty())
            throw new BadRequestException("il cliente deve registrasi con un ordine");
        registrazioneCliente.setIdCliente(null);
        Cliente cliente = utilCliente.registrazioneCliente(registrazioneCliente);
        cliente.setOrdini(new ArrayList<>());
        Cliente saved = clienteRepository.saveAndFlush(cliente);
        for(OrdineNascitaClienteDTO ordineDTO : registrazioneCliente.getOrdini()){
        serviceOrdine.creaOrdine(saved.getIdCliente(),ordineDTO);
        }
        return utilCliente.clienteToClienteDTO(saved);

    }


    @Override
    public ClienteDTO cerca (Long idCliente) {
        Cliente target = clienteRepository.findById(idCliente).orElseThrow(
                ()-> new NotFoundException("cliente non trovato o inesistente"));
        return utilCliente.clienteToClienteDTO(target);

    }


    @Override
    public ClienteDTO modifica(Long idCliente, ClienteDTO clienteDTO) {
        clienteDTO.setIdCliente(idCliente);
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



