package it.itsacademy.pizzeriaexpress.service;
import it.itsacademy.pizzeriaexpress.dto.RiderDTO;
import it.itsacademy.pizzeriaexpress.entity.Rider;
import org.mapstruct.Mapper;

import java.util.Collection;

public interface ServiceRider {
    public RiderDTO registra(RiderDTO riderDTO);
    public RiderDTO cerca(Long idRider);
    public RiderDTO modifica(Long idRider,RiderDTO riderDTO);
    public RiderDTO licenzia(Long idRider);
    Collection<RiderDTO> tuttiRiders();
}
