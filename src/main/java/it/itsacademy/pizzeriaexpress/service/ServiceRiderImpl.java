package it.itsacademy.pizzeriaexpress.service;


import it.itsacademy.pizzeriaexpress.dto.RiderDTO;
import it.itsacademy.pizzeriaexpress.entity.Rider;
import it.itsacademy.pizzeriaexpress.exception.NotFoundException;
import it.itsacademy.pizzeriaexpress.repository.RiderRepository;
import it.itsacademy.pizzeriaexpress.utility.RiderUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class ServiceRiderImpl implements ServiceRider {

    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private RiderUtility utilRider;

    @Override
    public RiderDTO registra(RiderDTO riderDTO) {
        Rider saved = riderRepository.save(utilRider.riderDTOToRider(riderDTO));
        return utilRider.riderToRiderDTO(saved);
    }

    @Override
    public RiderDTO cerca(Long idRider) {
        Rider target = riderRepository.findById(idRider).orElseThrow(()->new NotFoundException("Rider non trovato o inesistente"));
        return utilRider.riderToRiderDTO(target);
    }

    @Override
    public RiderDTO modifica(Long idRider, RiderDTO riderDTO) {
        riderDTO.setIdRider(idRider);
        Rider saved =  riderRepository.save(utilRider.riderDTOToRider(riderDTO));
        return utilRider.riderToRiderDTO(saved);
    }

    @Override
    public RiderDTO licenzia(Long idRider) {
        RiderDTO target = cerca(idRider);
        riderRepository.deleteById(idRider);
        return target;
    }

    @Override
    public Collection<RiderDTO> tuttiRiders() {
        return utilRider.tuttiRiders(riderRepository.findAll());
    }
}
