package it.itsacademy.pizzeriaexpress.controller;



import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.service.ServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ServiceCliente serviceCliente;

    @PostMapping(consumes ="application/json ", produces = "application/json ")
    public ClienteDTO registraCliente(@RequestBody ClienteDTO clienteDTO) {
        return serviceCliente.registrazione(clienteDTO);
    }
    @GetMapping(path = "/{id}", produces = "application/json ")
    public ClienteDTO cercaCliente(@PathVariable Long id) {
        return serviceCliente.cerca(id);
    }
    @GetMapping(produces = "application/json ")
    public Collection<ClienteDTO> tuttiIClienti() {
        return serviceCliente.tuttiClienti();
    }
    @DeleteMapping (path = "/{id}", produces = "application/json ")
    public ClienteDTO eliminaCliente(@PathVariable Long id) {
        return serviceCliente.cancella(id);
    }
}
