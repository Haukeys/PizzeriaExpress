package it.itsacademy.pizzeriaexpress.service;
import it.itsacademy.pizzeriaexpress.dto.*;
import it.itsacademy.pizzeriaexpress.entity.Cliente;
import it.itsacademy.pizzeriaexpress.entity.Ordine;
import it.itsacademy.pizzeriaexpress.entity.OrdinePrioritario;
import it.itsacademy.pizzeriaexpress.entity.Rider;
import it.itsacademy.pizzeriaexpress.exception.BadRequestException;
import it.itsacademy.pizzeriaexpress.exception.ConflictException;
import it.itsacademy.pizzeriaexpress.exception.NotFoundException;
import it.itsacademy.pizzeriaexpress.repository.ClienteRepository;
import it.itsacademy.pizzeriaexpress.repository.OrdineRepository;
import it.itsacademy.pizzeriaexpress.repository.RiderRepository;
import it.itsacademy.pizzeriaexpress.utility.OrdineUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
@Transactional
public class ServiceOrdineImpl implements ServiceOrdine {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OrdineUtility utilOrdine;

    @Autowired
    private ServicePizza pizzaService;

    @Autowired
    private RiderRepository riderRepository;

    @Override
    public OrdineDTO creaOrdine(Long idCliente ,OrdineNascitaClienteDTO ordineNascitaDTO){
        if (ordineRepository.existsById(ordineNascitaDTO.getCodice()))
            throw new ConflictException("Esiste già un ordine con codice " + ordineNascitaDTO.getCodice());


        Cliente clienteOrditore = clienteRepository.findById(idCliente).orElseThrow(
                () -> new NotFoundException(idCliente+":id del cliente inesistente o non trovato")
        );

        if (ordineNascitaDTO.getOrdini_pizze() == null || ordineNascitaDTO.getOrdini_pizze().isEmpty()) {
            throw new BadRequestException("L'ordine deve avere  almeno una pizza");
        }

        OrdineDTO daSalvare = utilOrdine.ordineNascitaClienteDTOToOrdine(ordineNascitaDTO);
        daSalvare.setOrdini_pizze(new ArrayList<>());

        for (OrdinePizzaNascitaDTO pizzaOrdinata : ordineNascitaDTO.getOrdini_pizze()) {

            PizzaDTO pizzaTrovata = pizzaService.trovaPizza(pizzaOrdinata.getIdPizza());

            OrdinePizzaDTO op = new OrdinePizzaDTO();
            op.setPizza(pizzaTrovata);
            op.setQuantita(pizzaOrdinata.getQuantita());

            daSalvare.getOrdini_pizze().add(op);
        }

        Ordine saved = ordineRepository.save(utilOrdine.ordineDTOToOrdine(daSalvare)); // ordine senza il suo cliente
        clienteOrditore.getOrdini().add(saved); // collega cliente con nuovo ordine

        // Cerca il rider se ordine prioritario
        if (ordineNascitaDTO.getIdRider() != null) {
            Rider riderTrovato = riderRepository.findById(ordineNascitaDTO.getIdRider())
                    .orElseThrow(() -> new NotFoundException("rider con id " + ordineNascitaDTO.getIdRider()+"non trovato o inesistente"));
            saved.setRider(riderTrovato);
        }

        return utilOrdine.ordineToOrdineDTO(saved);
    }

    @Override
    public OrdinePrioritarioDTO creaOrdinePrioritario(Long idCliente, OrdinePrioritarioNascitaClienteDTO nuovoOrdinePrioritario){
        Cliente clienteOrditore = clienteRepository.findById(idCliente).orElseThrow(
                () -> new NotFoundException(idCliente+":id del cliente inesistente o non trovato")
        );

        OrdinePrioritarioDTO toSaved = utilOrdine.odinePrioritarioNascitaClienteDTOToOrdine(nuovoOrdinePrioritario);
        toSaved.setOrdini_pizze(new ArrayList<>());

        for (OrdinePizzaNascitaDTO pizzaOrdinata : nuovoOrdinePrioritario.getOrdini_pizze()) {

            PizzaDTO pizzaTrovata = pizzaService.trovaPizza(pizzaOrdinata.getIdPizza());

            OrdinePizzaDTO op = new OrdinePizzaDTO();
            op.setPizza(pizzaTrovata);
            op.setQuantita(pizzaOrdinata.getQuantita());

            toSaved.getOrdini_pizze().add(op);
        }

        OrdinePrioritario saved = ordineRepository.save(utilOrdine.ordinePrioritarioDTOToOrdinePrioritario(toSaved)); // ordine senza il suo cliente
        clienteOrditore.getOrdini().add(saved); // collega cliente con nuovo ordine

        // Cerca il rider se ordine prioritario
        if (nuovoOrdinePrioritario.getIdRider() != null) {
            Rider riderTrovato = riderRepository.findById(nuovoOrdinePrioritario.getIdRider())
                    .orElseThrow(() -> new NotFoundException("rider con id " + nuovoOrdinePrioritario.getIdRider()+"non trovato o inesistente"));
            saved.setRider(riderTrovato);
        }

        return utilOrdine.ordinePrioritarioToOrdinePrioritarioDTO(saved);
    }

    @Override
    public OrdineDTO cercaOrdine(Long idCliente,String codice) {
        Cliente clienteDelOrdine = clienteRepository.findById(idCliente).orElseThrow(()-> new NotFoundException("Il cliente che ha fatto l ordine non è stato trovato/inesistente"));
        Ordine found = clienteDelOrdine.getOrdini().stream()
                .filter(ordine -> ordine.getCodice().equals(codice))
                .findFirst().orElseThrow(()-> new NotFoundException("Ordine con "+codice+" non trovato"));
        return utilOrdine.ordineToOrdineDTO(found);
    }

    @Override
    public OrdineDTO modificaOrdine(Long idCliente, String codice, OrdineDTO ordineDTO) {
        ordineDTO.setCodice(codice);
        cercaOrdine(idCliente, codice);
        Ordine saved = ordineRepository.save(utilOrdine.ordineDTOToOrdine(ordineDTO));
        return utilOrdine.ordineToOrdineDTO(saved);
    }

    @Override
    public OrdineDTO cancellaOrdine(Long idCliente, String codice) {
        OrdineDTO target = cercaOrdine(idCliente,codice);
        clienteRepository.deleteById(idCliente);
        return target;
    }

    @Override
    public Collection<OrdineDTO> tuttiOrdini(){
       return utilOrdine.tuttiOrdini(ordineRepository.findAll());
   }

    @Override
    public Collection<OrdineDTO> tuttiOrdiniNonPrioritari() {
        return utilOrdine.tuttiOrdini(ordineRepository.findAll()
                .stream()
                .filter((ord) -> !(ord instanceof OrdinePrioritario))
                .toList());
    }

    @Override
    public Collection<OrdinePrioritarioDTO> tuttiOrdiniPrioritari() {
        return utilOrdine.tuttiOrdiniPrioritari(ordineRepository.findAll()
                .stream()
                .filter((ord) -> ord instanceof OrdinePrioritario)
                .map((ord) -> (OrdinePrioritario) ord)
                .toList());
    }

    @Override
    public OrdineDTO aggiungiPizza(Long idCliente, String codice, PizzaDTO pizza ,Integer quantita) {
        PizzaDTO p = pizzaService.trovaPizza(idCliente);

        OrdineDTO ordineDTO = cercaOrdine(idCliente,codice);
        ordineDTO.getOrdini_pizze().add(new OrdinePizzaDTO(quantita, pizza));

        Ordine saved = ordineRepository.save(utilOrdine.ordineDTOToOrdine(ordineDTO));
        return utilOrdine.ordineToOrdineDTO(saved);
    }

}
