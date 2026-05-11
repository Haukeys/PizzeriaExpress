package it.itsacademy.pizzeriaexpress.service;

import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;
import it.itsacademy.pizzeriaexpress.entity.Pizza;
import it.itsacademy.pizzeriaexpress.exception.NotFoundException;
import it.itsacademy.pizzeriaexpress.repository.PizzaRepository;
import it.itsacademy.pizzeriaexpress.utility.PizzaUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicePizzaImpl implements ServicePizza {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaUtility utilPizza;

    @Override
    public PizzaDTO creaPizza(PizzaDTO pizzaDTO) {
        Pizza saved = pizzaRepository.save(utilPizza.pizzaDTOToPizza(pizzaDTO));
        return utilPizza.pizzaToPizzaDTO(saved);
    }

    @Override
    public PizzaDTO modificaPizza(Long id,PizzaDTO pizzaDTO) {
        pizzaDTO.setId(id);
        Pizza saved = pizzaRepository.save(utilPizza.pizzaDTOToPizza(pizzaDTO));
        return utilPizza.pizzaToPizzaDTO(saved);
    }

    @Override
    public PizzaDTO trovaPizza(Long id) {
        Pizza target = pizzaRepository.findById(id).orElseThrow(()-> new NotFoundException("Pizza non trovata")
        );
        return utilPizza.pizzaToPizzaDTO(target);

    }

    @Override
    public Collection<PizzaDTO> tutteLePizze() {
        return utilPizza.tutteLePizze(pizzaRepository.findAll());
    }

    @Override
    public PizzaDTO cancellaPizza(Long id) {
        PizzaDTO target = trovaPizza(id);
        pizzaRepository.deleteById(id);
        return target;

    }
}
