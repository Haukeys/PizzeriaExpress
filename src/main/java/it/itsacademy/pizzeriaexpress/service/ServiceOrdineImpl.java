package it.itsacademy.pizzeriaexpress.service;
import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;
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
import java.util.List;

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

        if(ordineDTO.getOrdini_pizze()==null || ordineDTO.getOrdini_pizze().isEmpty()){
            throw new BadRequestException("L'ordine deve contenere almeno una pizza");
        }

       Ordine saved = ordineRepository.save(utilOrdine.ordineDTOToOrdine(ordineDTO));
       clienteDelOrdine.getOrdini().add(saved);

       return utilOrdine.ordineToOrdineDTO(saved);
    }

    @Override
    public OrdineDTO cercaOrdine(Long id, String codice) {
        return null;
    }

    @Override
    public OrdineDTO modificaOrdine(Long id, String codice, OrdineDTO ordineDTO) {
        return null;
    }

    @Override
    public OrdineDTO cancellaOrdine(Long id, String Codice) {
        return null;
    }

    @Override
    public Collection<OrdineDTO> tuttiOrdini() {
        return List.of();
    }

    @Override
    public OrdineDTO aggiungiPizza(Long id, String codice, PizzaDTO pizzaDTO, Integer quantita) {
        return null;
    }

}
