package it.itsacademy.pizzeriaexpress.repository;

import it.itsacademy.pizzeriaexpress.entity.Pizza;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class PizzaRepositoryTest {

    @Autowired
    private PizzaRepository pizzaRepository;


    @Test
    public void testPizzaSave() {
        Pizza pizza = new Pizza();
        pizza.setId(1L);
        pizza.setNome("Margherita");
        pizza.setDescrizione("Pomodoro, Mozzarella, Basilico");
        pizza.setPrezzo(5.99);

        Pizza savedPizza = pizzaRepository.save(pizza);
        assertNotNull(savedPizza);
        assertEquals(pizza.getId(), savedPizza.getId());

    }
    @Test
    public void testPizzaUpdate() {
        Pizza pizza = new Pizza();
        pizza.setId(1L);
        pizza.setNome("Rossa");
        pizza.setDescrizione("Pomodoro,Origano");
        pizza.setPrezzo(7.00);
        Pizza saved = pizzaRepository.save(pizza);

        saved.setNome("Margherita");
        Pizza updated = pizzaRepository.save(saved);

        assertEquals("Margherita", updated.getNome());
    }

    @Test
    public void testPizzaFindById() {
        Pizza pizza = new Pizza();
        pizza.setId(1L);
        pizza.setNome("Margherita");
        Pizza saved = pizzaRepository.save(pizza);

        Optional<Pizza> found = pizzaRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals(saved.getId(), found.get().getId());
    }

    @Test
    public void testPizzaDelete() {
        Pizza pizza = new Pizza();
        pizza.setId(1L);
        pizza.setNome("Calzone");
        Pizza saved = pizzaRepository.save(pizza);

        pizzaRepository.deleteById(saved.getId());
        Optional<Pizza> deleted = pizzaRepository.findById(saved.getId());

        assertFalse(deleted.isPresent());
    }
    @Test
    public void testPizzaFindAll() {
        //
        Pizza p1 = new Pizza();
        p1.setId(1L);
        p1.setNome("Margherita");
        p1.setDescrizione("Pomodoro, Mozzarella, Basilico");
        p1.setPrezzo(6.00);
        pizzaRepository.save(p1);

        Pizza p2 = new Pizza();
        p2.setId(1L);
        p2.setNome("4 Formaggi");
        p2.setDescrizione("mozzarella,pecorino,gorgonzola,buratta");
        p2.setPrezzo(8.50);
        pizzaRepository.save(p2);


        Collection<Pizza> pizzas = pizzaRepository.findAll();


        assertNotNull(pizzas);

    }
}

