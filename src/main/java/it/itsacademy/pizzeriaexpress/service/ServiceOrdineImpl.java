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
public class ServiceOrdineImpl implements ServiceOrdine {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private OrdineUtility utilOrdine;



    @Override
    public OrdineDTO crea(OrdineDTO ordineDTO) {
        return null;
    }

    @Override
    public OrdineDTO cerca(String codice) {
        return null;
    }

    @Override
    public OrdineDTO modifica(String codice, OrdineDTO ordineDTO) {
        return null;
    }

    @Override
    public OrdineDTO cancella(String Codice) {
        return null;
    }


}
