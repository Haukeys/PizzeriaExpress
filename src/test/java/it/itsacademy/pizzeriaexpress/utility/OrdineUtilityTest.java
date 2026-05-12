package it.itsacademy.pizzeriaexpress.utility;

import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;

import it.itsacademy.pizzeriaexpress.entity.Ordine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring", uses ={OrdinePizzaUtility.class})//car utiliser dans cette classe {} facilite l autocreation/complilation
public interface OrdineUtilityTest {
    //@Mapping(target = "id", ignore = true)
    public Ordine ordineDTOToOrdine(OrdineDTO ordineDTO);
    public OrdineDTO ordineToOrdineDTO(Ordine ordine);
    public Collection<OrdineDTO> tuttiOrdini(Collection<Ordine> ordini);
}
