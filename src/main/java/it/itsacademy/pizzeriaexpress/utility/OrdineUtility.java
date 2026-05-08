package it.itsacademy.pizzeriaexpress.utility;


import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.dto.OrdineDTO;
import it.itsacademy.pizzeriaexpress.entity.Cliente;
import it.itsacademy.pizzeriaexpress.entity.Ordine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdineUtility {

    public Ordine clienteDTOToOrdine(ClienteDTO clienteDTO);
    public OrdineDTO clienteToOrdineDTO(Cliente cliente);

}
