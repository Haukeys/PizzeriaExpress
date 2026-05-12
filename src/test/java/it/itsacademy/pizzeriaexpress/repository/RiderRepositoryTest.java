package it.itsacademy.pizzeriaexpress.repository;


import it.itsacademy.pizzeriaexpress.entity.Cliente;
import it.itsacademy.pizzeriaexpress.entity.Rider;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class RiderRepositoryTest {

    @Autowired
    private RiderRepository riderRepository;

    @Test
    public void testRegistraRider(){

        Rider r = new Rider();

        r.setNome("Avril Lavingne");

        Rider saved = riderRepository.save(r);

        assertNotNull(saved);//on verifie que c est pas vide / null
        assertEquals(r.getId(), saved.getId());// verification que c est le bon object/entiter

    }
     @Test
     public void testModificaRider() {

         Rider r = new Rider();

         r.setNome("Avril Lavingne");

         Rider saved = riderRepository.save(r);

         saved.setNome("Bruno Mars");

         Rider updated = riderRepository.save(saved);

         assertEquals("Bruno Mars", updated.getNome());

     }
     @Test
     public void testCercaRider(){

         Rider r = new Rider();

         r.setNome("ED Sherand");

         Rider saved = riderRepository.save(r);

         Optional<Rider> found = riderRepository.findById(saved.getId());

         assertTrue(found.isPresent());//on verifie que objet est present
         assertEquals(saved.getId(), found.get().getId());//on verifie si cest le bonne object

         }
     @Test
     public void testLicenziaRider(){

         Rider r = new Rider();
         r.setNome("Ed Sherand");

         Rider saved = riderRepository.save(r);

         riderRepository.deleteById(saved.getId());
         Optional<Rider> deleted = riderRepository.findById(saved.getId());

         assertFalse(deleted.isPresent());//on verifie que l object soit eliminer
    }
     @Test
    public void testTuttiIRiders(){

        Rider r1 = new Rider();
        r1.setNome("Ed Sherand");
        riderRepository.save(r1);

        Rider r2 = new Rider();
        r2.setNome("Bruno Mars");
        riderRepository.save(r2);

        Collection<Rider> riders = riderRepository.findAll();
        assertNotNull(riders);
        assertEquals(2, riders.size());

     }
    }



