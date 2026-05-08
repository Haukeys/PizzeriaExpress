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
import java.util.List;
import java.util.stream.Collectors;

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
    public RiderDTO cerca(Long id) {
        Rider target = riderRepository.findById(id).orElseThrow(()->new NotFoundException("Rider non trovato o inesistente"));
        return utilRider.riderToRiderDTO(target);
    }

    @Override
    public RiderDTO Licenzia(Long id) {
        RiderDTO target = cerca(id);
        riderRepository.deleteById(id);
        return target;
    }

    @Override
    public List<RiderDTO> tuttiRiders() {
        return riderRepository.findAll()
                .stream()
                .map(utilRider::riderToRiderDTO)
                .collect(Collectors.toList());
    }
}
