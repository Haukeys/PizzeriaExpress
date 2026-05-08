package it.itsacademy.pizzeriaexpress.utility;
import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;

import it.itsacademy.pizzeriaexpress.entity.Ordine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdineUtility {

    public Ordine ordineDTOToOrdine(OrdineDTO ordineDTO);
    public OrdineDTO ordineToOrdineDTO(Ordine ordine);

}
