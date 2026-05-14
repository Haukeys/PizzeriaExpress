package it.itsacademy.pizzeriaexpress.service;

import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.dto.PizzaDTO;
import it.itsacademy.pizzeriaexpress.entity.Pizza;
import it.itsacademy.pizzeriaexpress.repository.PizzaRepository;
import it.itsacademy.pizzeriaexpress.utility.PizzaUtility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServicePizzaImplTest {

    @Mock
    private PizzaRepository pizzaRepository;

    @Mock
    private PizzaUtility pizzaMapper;

    @InjectMocks
    private ServicePizzaImpl pizzaService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePizza() {

        Pizza pizzaEntity = new Pizza();
        pizzaEntity.setIdPizza(1L);
        pizzaEntity.setNome("Margherita");
        pizzaEntity.setDescrizione("Pomodoro, Mozzarella, Basilico");
        pizzaEntity.setPrezzo(9.00);

        when(pizzaRepository.findById(1L)).thenReturn(java.util.Optional.of(pizzaEntity));
        when(pizzaMapper.pizzaToPizzaDTO(pizzaEntity)).thenReturn(new PizzaDTO(1L, "Margherita", "Pomodoro, Mozzarella, Basilico", 9.00));
        assertEquals("Margherita", pizzaService.trovaPizza(1L).getNome());
    }
    @Test
    public void testTrovaPizza() {
        // on recree l entiter
        Pizza pizzaEntity = new Pizza();
        pizzaEntity.setIdPizza(1L);
        pizzaEntity.setNome("Margherita");

        PizzaDTO pizzaDTO = new PizzaDTO(1L, "Margherita", "Desc", 9.00);

        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizzaEntity));
        when(pizzaMapper.pizzaToPizzaDTO(pizzaEntity)).thenReturn(pizzaDTO);


        PizzaDTO result = pizzaService.trovaPizza(1L);

        assertNotNull(result);
        assertEquals("Margherita", result.getNome());
    }

    @Test
    public void testModificaPizza() {

        Long id = 1L;
        PizzaDTO inputDTO = new PizzaDTO(null, "Capricciosa", "Molto buona", 10.00);
        Pizza pizzaEntity = new Pizza();
        Pizza savedPizza = new Pizza();
        PizzaDTO outputDTO = new PizzaDTO(id, "Capricciosa", "Molto buona", 10.00);

        when(pizzaMapper.pizzaDTOToPizza(any(PizzaDTO.class))).thenReturn(pizzaEntity);
        when(pizzaRepository.save(any(Pizza.class))).thenReturn(savedPizza);
        when(pizzaMapper.pizzaToPizzaDTO(any(Pizza.class))).thenReturn(outputDTO);


        PizzaDTO result = pizzaService.modificaPizza(id, inputDTO);


        assertNotNull(result);
        assertEquals("Capricciosa", result.getNome());
        assertEquals(id, result.getIdPizza());
    }
    @Test
    public void testCancellaPizza() {

        Long id = 1L;
        Pizza pizzaEntity = new Pizza();
        PizzaDTO pizzaDTO = new PizzaDTO(id, "Margherita", "Desc", 9.00);


        when(pizzaRepository.findById(id)).thenReturn(Optional.of(pizzaEntity));
        when(pizzaMapper.pizzaToPizzaDTO(pizzaEntity)).thenReturn(pizzaDTO);


        PizzaDTO result = pizzaService.cancellaPizza(id);


        assertNotNull(result);
        assertEquals(id, result.getIdPizza());
        //on verifie que l actiondu service  a ete communiquer a la base de donnee. pour une suppression on fait une verification supplementaire
        verify(pizzaRepository, times(1)).deleteById(id);//1:on veut exactement un seul appel de la methode. time sert a voir la frequence d appel.
    }

    @Test
    public void testTutteLePizze(){

        //entiter1
        Pizza p1 = new Pizza();
        p1.setIdPizza(1L);
        p1.setNome("Margherita");
        p1.setDescrizione("Pomodoro,mozzarella,basilico");
        p1.setPrezzo(9.00);

        //entiter2
        Pizza p2 = new Pizza();
        p2.setIdPizza(2L);
        p2.setNome("4 Formaggi");
        p2.setDescrizione("mozzarella,pecorino,gorgonzola,buratta");
        p2.setPrezzo(10.00);

        List<Pizza> pizze = Arrays.asList(p1, p2);

        //dto correspondants

        Collection<PizzaDTO> pizzeDTO = Arrays.asList(
                new PizzaDTO(1L,"Margherita","Pomodoro,mozzarella,basilico",9.00),
                new PizzaDTO(2L, "4 Formaggi","mozzarella,pecorino,gorgonzola,buratta",10.00)
        );

        when(pizzaRepository.findAll()).thenReturn(pizze);
        when(pizzaMapper.tutteLePizze(pizze)).thenReturn(pizzeDTO);

        Collection<PizzaDTO> result = pizzaService.tutteLePizze();

        assertNotNull(result);
        assertEquals(2, result.size(), "La collection deve contenere 2 pizze");

        // Vérification des appels
        //on verifie que l actiondu service  a ete communiquer a la base de donnee. pour une suppression on fait une verification supplementaire
        //1:on veut exactement un seul appel de la methode. time sert a voir la frequence d appel.

        verify(pizzaRepository, times(1)).findAll();
        verify(pizzaMapper, times(1)).tutteLePizze(pizze);

    }

}