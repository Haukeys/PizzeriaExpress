package it.itsacademy.pizzeriaexpress.utility;
import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;

import it.itsacademy.pizzeriaexpress.dto.OrdineNascitaClienteDTO;
import it.itsacademy.pizzeriaexpress.entity.Ordine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring", uses ={OrdinePizzaUtility.class})//car utiliser dans cette classe {} facilite l autocreation/complilation
public interface OrdineUtility {

    public Ordine ordineDTOToOrdine(OrdineDTO ordineDTO);

    @Mapping(target = "rider", ignore = true)
    public OrdineDTO ordineDTOToOrdine(OrdineNascitaClienteDTO ordineNascitaDTO);
    public OrdineDTO ordineToOrdineDTO(Ordine ordine);
    public Collection<OrdineDTO> tuttiOrdini(Collection<Ordine> ordini);
}
