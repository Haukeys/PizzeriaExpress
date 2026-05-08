package it.itsacademy.pizzeriaexpress.service;
import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;
import it.itsacademy.pizzeriaexpress.entity.Ordine;
import it.itsacademy.pizzeriaexpress.repository.OrdineRepository;
import it.itsacademy.pizzeriaexpress.utility.OrdineUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServiceOrdineImpl implements OrdineUtility {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private OrdineUtility ordineUtility;


    @Override
    public Ordine ordineDTOToOrdine(OrdineDTO ordineDTO) {
        return null;
    }

    @Override
    public OrdineDTO ordineToOrdineDTO(Ordine ordine) {
        return null;
    }
}
