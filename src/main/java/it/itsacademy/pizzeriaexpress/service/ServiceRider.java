package it.itsacademy.pizzeriaexpress.service;
import it.itsacademy.pizzeriaexpress.dto.RiderDTO;

import java.util.Collection;
import java.util.List;

public interface ServiceRider {
    public RiderDTO registra(RiderDTO riderDTO);
    public RiderDTO cerca(Long id);
    public RiderDTO Licenzia(Long id);
    public List<RiderDTO> tuttiRiders();
}
