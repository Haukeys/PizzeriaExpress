package it.itsacademy.pizzeriaexpress.controller;


import it.itsacademy.pizzeriaexpress.dto.RiderDTO;
import it.itsacademy.pizzeriaexpress.service.ServiceRider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/riders")
public class RiderController {

    @Autowired
    private ServiceRider serviceRider;

    @PostMapping(consumes ="application/json ", produces = "application/json ")
    public RiderDTO registra(@RequestBody RiderDTO riderDTO) {
        return serviceRider.registra(riderDTO);
    }
    @GetMapping(path = "/{id}", produces = "application/json ")
    public RiderDTO cercaRider(@PathVariable Long idRider) {
        return serviceRider.cerca(idRider);
    }
    @GetMapping(produces = "application/json ")
    public Collection<RiderDTO> tuttiIRiders() {
        return serviceRider.tuttiRiders();
    }
    @DeleteMapping (path = "/{id}", produces = "application/json ")
    public RiderDTO licenziaRider(@PathVariable Long idRider) {
        return serviceRider.licenzia(idRider);
    }
}
