package it.itsacademy.pizzeriaexpress.repository;


import it.itsacademy.pizzeriaexpress.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void shouldSaveAndFindCliente(){
        // creation de lobject/entiter
        Cliente c = new Cliente();
        c.setNome("Cyril MV");
        c.setIndirizzo("via mosca 1");
        c.setTelefono("818718717");

        //sauvegarde ou enregistrement de l object/e
        Cliente saved = clienteRepository.save(c);

        //on verifie que c est sauvegarder et si les donner sont egale a ce que l on met en input dans assertEquals
        assertNotNull(saved);
        assertEquals("Cyril MV",saved.getNome());
    }
}
