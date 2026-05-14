package it.itsacademy.pizzeriaexpress.service;


import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.dto.OrdineNascitaClienteDTO;
import it.itsacademy.pizzeriaexpress.dto.OrdinePizzaNascitaDTO;
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

    @Override
    public ClienteDTO registrazione(RegistrazioneClienteDTO registrazioneDTO) {
// 1. Création du Cliente
        Cliente cliente = new Cliente();
        cliente.setNome(registrazioneDTO.getNome());
        cliente.setIndirizzo(registrazioneDTO.getIndirizzo());
        cliente.setTelefono(registrazioneDTO.getTelefono());

        // Initialisation de la collection du client (car @OneToMany)
        List<Ordine> listaOrdini = new ArrayList<>();

        if (registrazioneDTO.getOrdini() != null) {
            for (OrdineNascitaClienteDTO oDto : registrazioneDTO.getOrdini()) {

                Ordine ordineEntity;

                // Logique d'héritage
                if (oDto.getIdRider() != null) {
                    OrdinePrioritario op = new OrdinePrioritario();
                    Rider r = riderRepository.findById(oDto.getIdRider())
                            .orElseThrow(() -> new RuntimeException("Rider non trovato"));
                    op.setRider(r);
                    op.setSovrapprezzo(5.0);
                    ordineEntity = op;
                } else {
                    ordineEntity = new Ordine();
                }

                ordineEntity.setCodice(oDto.getCodice());

                // 2. Gestion des pizzas (Collection interne à Ordine)
                List<OrdinePizza> lignesPizze = new ArrayList<>();
                for (OrdinePizzaNascitaDTO pDto : oDto.getOrdini_pizze()) {
                    Pizza pizza = pizzaRepository.findById(pDto.getIdPizza())
                            .orElseThrow(() -> new RuntimeException("Pizza non trovata"));

                    OrdinePizza riga = new OrdinePizza();
                    riga.setPizza(pizza);
                    riga.setQuantita(pDto.getQuantita());

                    // IMPORTANT : On n'appelle PAS setOrdine() car il n'existe pas dans ton schéma
                    lignesPizze.add(riga);
                }

                // On affecte la collection de pizzas à l'ordre
                ordineEntity.setOrdini_pizze(lignesPizze);

                // On ajoute l'ordre à la liste du client
                listaOrdini.add(ordineEntity);
            }
        }

        // 3. On affecte la liste d'ordres au client
        cliente.setOrdini(listaOrdini);

        // 4. Sauvegarde
        // Note : Ton schéma utilise CascadeType.PERSIST, ce qui est parfait ici
        Cliente clienteSalvato = clienteRepository.save(cliente);

        return utilCliente.clienteToClienteDTO(clienteSalvato);

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



