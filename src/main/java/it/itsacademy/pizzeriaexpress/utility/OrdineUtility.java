package it.itsacademy.pizzeriaexpress.utility;
import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;

import it.itsacademy.pizzeriaexpress.entity.Ordine;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface OrdineUtility {

    public Ordine ordineDTOToOrdine(OrdineDTO ordineDTO);
    public OrdineDTO ordineToOrdineDTO(Ordine ordine);
    public Collection<OrdineDTO> tuttiOrdini(Collection<Ordine> ordini);
}
