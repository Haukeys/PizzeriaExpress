package it.itsacademy.pizzeriaexpress.utility;


import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteUtility {

    public  Cliente clienteDTOToCliente(ClienteDTO clienteDTO);
    public  ClienteDTO clienteToClienteDTO(Cliente cliente);

}
