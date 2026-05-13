package it.itsacademy.pizzeriaexpress.service;
import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;
import it.itsacademy.pizzeriaexpress.entity.Cliente;
import it.itsacademy.pizzeriaexpress.entity.Ordine;
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
   public OrdineDTO crea(Long id,OrdineDTO ordineDTO) {
   Cliente clienteDelOrdine = clienteRepository.findById(id).orElseThrow(()-> new NotFoundException("Il cliente che ha fatto l ordine non è stato trovato/inesistente"));

       Ordine saved = ordineRepository.save(utilOrdine.ordineDTOToOrdine(ordineDTO));
       clienteDelOrdine.getOrdini().add(saved);

       return utilOrdine.ordineToOrdineDTO(saved);
    }
//
//    @Override
//    public OrdineDTO cerca(String codice) {
//        Ordine target = ordineRepository.findById(codice).orElseThrow(()-> new NotFoundException("Ordine non trovato"));
//        return utilOrdine.ordineToOrdineDTO(target);
//    }
//
//    @Override
//    public OrdineDTO modifica(String codice, OrdineDTO ordineDTO) {
//        ordineDTO.setCodice(codice);
//        Ordine saved = ordineRepository.save(utilOrdine.ordineDTOToOrdine(ordineDTO));
//        return utilOrdine.ordineToOrdineDTO(saved);
//    }
//
//    @Override
//    public OrdineDTO cancella(String Codice) {
//        OrdineDTO target= cerca(Codice);
//        ordineRepository.deleteById(Codice);
//        return target;
//    }
//
//    @Override
//    public Collection<OrdineDTO> tuttiOrdini() {
//        return utilOrdine.tuttiOrdini(ordineRepository.findAll());
//    }
}
