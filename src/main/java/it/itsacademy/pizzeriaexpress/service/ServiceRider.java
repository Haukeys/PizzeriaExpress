package it.itsacademy.pizzeriaexpress.service;
import it.itsacademy.pizzeriaexpress.dto.RiderDTO;
import it.itsacademy.pizzeriaexpress.entity.Rider;
import org.mapstruct.Mapper;

import java.util.Collection;

public interface ServiceRider {
    public RiderDTO registra(RiderDTO riderDTO);
    public RiderDTO cerca(Long id);
    public RiderDTO modifica(Long id,RiderDTO riderDTO);
    public RiderDTO Licenzia(Long id);
    Collection<RiderDTO> tuttiRiders();
}
