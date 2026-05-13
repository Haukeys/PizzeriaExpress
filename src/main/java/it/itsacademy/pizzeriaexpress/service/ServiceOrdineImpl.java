package it.itsacademy.pizzeriaexpress.service;
import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;
import it.itsacademy.pizzeriaexpress.dto.OrdinePizzaDTO;
import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;
import it.itsacademy.pizzeriaexpress.entity.Cliente;
import it.itsacademy.pizzeriaexpress.entity.Ordine;
import it.itsacademy.pizzeriaexpress.exception.BadRequestException;
import it.itsacademy.pizzeriaexpress.exception.NotFoundException;
import it.itsacademy.pizzeriaexpress.repository.ClienteRepository;
import it.itsacademy.pizzeriaexpress.repository.OrdineRepository;
import it.itsacademy.pizzeriaexpress.utility.OrdineUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

   @Override
   public OrdineDTO creaOrdine(Long id,OrdineDTO ordineDTO) {

   Cliente clienteDelOrdine = clienteRepository.findById(id).orElseThrow(()-> new NotFoundException("Il cliente che ha fatto l ordine non è stato trovato/inesistente"));

   //controllo che un ordine abbia almeno una pizza
        if(ordineDTO.getOrdini_pizze()==null || ordineDTO.getOrdini_pizze().isEmpty()){
            throw new BadRequestException("L'ordine deve contenere almeno una pizza");
        }

       Ordine saved = ordineRepository.save(utilOrdine.ordineDTOToOrdine(ordineDTO));
       clienteDelOrdine.getOrdini().add(saved);

       return utilOrdine.ordineToOrdineDTO(saved);
    }

    @Override
    public OrdineDTO cercaOrdine(Long id, String codice) {
        Cliente clienteDelOrdine = clienteRepository.findById(id).orElseThrow(()-> new NotFoundException("Il cliente che ha fatto l ordine non è stato trovato/inesistente"));
        Ordine found = clienteDelOrdine.getOrdini().stream()
                .filter(ordine -> ordine.getCodice().equals(codice))
                .findFirst().orElseThrow(()-> new NotFoundException("Ordine con "+codice+" non trovato"));
        return utilOrdine.ordineToOrdineDTO(found);
    }

    @Override
    public OrdineDTO modificaOrdine(Long id, String codice, OrdineDTO ordineDTO) {
        ordineDTO.setCodice(codice);
        cercaOrdine(id, codice);
        Ordine saved = ordineRepository.save(utilOrdine.ordineDTOToOrdine(ordineDTO));
        return utilOrdine.ordineToOrdineDTO(saved);
    }

    @Override
    public OrdineDTO cancellaOrdine(Long id, String codice) {
        OrdineDTO target = cercaOrdine(id,codice);
        clienteRepository.deleteById(id);
        return target;
    }

    @Override
    public Collection<OrdineDTO> tuttiOrdini(){
       return utilOrdine.tuttiOrdini(ordineRepository.findAll());
   }

    @Override
    public OrdineDTO aggiungiPizza(Long id, String codice, PizzaDTO pizza ,Integer quantita) {
        PizzaDTO p = pizzaService.trovaPizza(id);

        OrdineDTO ordineDTO = cercaOrdine(id,codice);
        ordineDTO.getOrdini_pizze().add(new OrdinePizzaDTO(quantita, pizza));

        Ordine saved = ordineRepository.save(utilOrdine.ordineDTOToOrdine(ordineDTO));
        return utilOrdine.ordineToOrdineDTO(saved);
    }

}
