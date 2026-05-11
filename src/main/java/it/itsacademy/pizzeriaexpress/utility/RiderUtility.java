package it.itsacademy.pizzeriaexpress.utility;

import it.itsacademy.pizzeriaexpress.dto.RiderDTO;
import it.itsacademy.pizzeriaexpress.entity.Rider;
import org.mapstruct.Mapper;

import java.util.Collection;


@Mapper(componentModel="spring")
public interface RiderUtility {

    public Rider riderDTOToRider(RiderDTO riderDTO);
    public RiderDTO riderToRiderDTO(Rider rider);
    public Collection<RiderDTO> tuttiRiders(Collection<Rider> riders);
}
