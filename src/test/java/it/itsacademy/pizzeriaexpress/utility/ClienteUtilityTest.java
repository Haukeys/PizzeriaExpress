package it.itsacademy.pizzeriaexpress.utility;


import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.entity.Cliente;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ClienteUtilityTest {
    public Cliente clienteDTOToCliente(ClienteDTO clienteDTO);
    public  ClienteDTO clienteToClienteDTO(Cliente cliente);
    public Collection<ClienteDTO> tuttiClienti(Collection<Cliente> clienti);
}


