package it.itsacademy.pizzeriaexpress.controller;



import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.dto.RegistrazioneClienteDTO;
import it.itsacademy.pizzeriaexpress.service.ServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ServiceCliente serviceCliente;

    // CHANGEMENT : On reçoit le RegistrazioneClienteDTO (celui avec les IDs)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ClienteDTO registraCliente(@RequestBody RegistrazioneClienteDTO registrazioneDTO) {

        // On appelle la méthode de naissance que nous avons codée
        return serviceCliente.registrazione(registrazioneDTO);
    }
    @GetMapping(path = "/{idCliente}", produces = "application/json ")
    public ClienteDTO cercaCliente(@PathVariable Long idCliente) {
        return serviceCliente.cerca(idCliente);
    }
    @GetMapping(produces = "application/json ")
    public Collection<ClienteDTO> tuttiIClienti() {
        return serviceCliente.tuttiClienti();
    }
    @DeleteMapping (path = "/{idCliente}", produces = "application/json ")
    public ClienteDTO eliminaCliente(@PathVariable Long idCliente) {
        return serviceCliente.cancella(idCliente);
    }
}
