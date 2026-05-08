package it.itsacademy.pizzeriaexpress;

import it.itsacademy.pizzeriaexpress.repository.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PizzeriaExpressApplicationTests {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
    void contextLoads() {
    }

}
