package it.itsacademy.pizzeriaexpress.service;


import it.itsacademy.pizzeriaexpress.dto.RiderDTO;

import it.itsacademy.pizzeriaexpress.entity.Rider;
import it.itsacademy.pizzeriaexpress.repository.RiderRepository;
import it.itsacademy.pizzeriaexpress.utility.RiderUtility;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceRiderImplTest {

    @Mock
    private RiderRepository riderRepository;

    @Mock
    private RiderUtility riderUtility;

    @InjectMocks
    private ServiceRiderImpl serviceRiderImpl;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistraRider() {
        Rider r = new Rider();
        r.setNome("Peter Parcker");

        when(riderRepository.findById(1L)).thenReturn(java.util.Optional.of(r));
        when(riderUtility.riderToRiderDTO(r)).thenReturn(new RiderDTO(1L,"Peter Parcker"));
        assertEquals("Peter Parcker",serviceRiderImpl.cerca(1L).getNome());

    }
    @Test
    public void testCercaRider(){
        Rider r = new Rider();
        r.setNome("Peter Parcker");

        RiderDTO riderDTO = new RiderDTO(1L,"Peter Parcker");
        when(riderRepository.findById(1L)).thenReturn(java.util.Optional.of(r));
        when(riderUtility.riderToRiderDTO(r)).thenReturn(riderDTO);

        RiderDTO result = serviceRiderImpl.cerca(1L);

        assertNotNull(result);
        assertEquals("Peter Parcker",result.getNome());

    }
    @Test
    public void testModificaRider(){

        Long id = 1L;
        RiderDTO inputDTO = new RiderDTO(null,"Jean Grey");
        Rider r = new Rider();
        Rider savedRider = new Rider();
        RiderDTO outputDTO = new RiderDTO(id,"Jean Grey");

        when(riderUtility.riderDTOToRider(any(RiderDTO.class))).thenReturn(r);
        when(riderRepository.save(any(Rider.class))).thenReturn(savedRider);
        when(riderUtility.riderToRiderDTO(any(Rider.class))).thenReturn(outputDTO);

        RiderDTO result = serviceRiderImpl.modifica(id,inputDTO);

        assertNotNull(result);
        assertEquals("Jean Grey",result.getNome());
        assertEquals(id,result.getIdRider());


    }
    @Test
    public void testLicenziaRider(){

        Long id = 1L;
        Rider r = new Rider();
        RiderDTO riderDTO = new RiderDTO(id , "Peter Parcker");

        when(riderRepository.findById(id)).thenReturn(java.util.Optional.of(r));
        when(riderUtility.riderToRiderDTO(r)).thenReturn(riderDTO);

        RiderDTO result = serviceRiderImpl.licenzia(id);

        assertNotNull(result);
        assertEquals(id,result.getIdRider());

        //on verifie que l actiondu service  a ete communiquer a la base de donnee. pour une suppression on fait une verification supplementaire
        //1:on veut exactement un seul appel de la methode. time sert a voir la frequence d appel.
        verify(riderRepository, times(1)).deleteById(id);

    }

    @Test
    public void testTuttiIClienti() {

        // Création de la première entité réelle
        Rider r1 = new Rider();
        r1.setNome("Peter Parcker");

        // Création de la deuxième entité réelle
        Rider r2 = new Rider();
        r2.setNome("Jiraya Lecochon");


        // Liste d'entités pour le repository
        List<Rider> riders = Arrays.asList(r1, r2);

        // On crée les DTO correspondants (ce que le mapper doit renvoyer)
        Collection<RiderDTO> ridersDTO = Arrays.asList(
                new RiderDTO(1L, "Peter Parcker"),
                new RiderDTO(2L,"Jiraya Lecochon")
    );

        when(riderRepository.findAll()).thenReturn(riders);
        when(riderUtility.tuttiRiders(riders)).thenReturn(ridersDTO);


        Collection<RiderDTO> result = serviceRiderImpl.tuttiRiders();

        assertNotNull(result);
        assertEquals(2, result.size(), "la collection deve contenere 2 Riders");

        // Vérification des appels
        //on verifie que l actiondu service  a ete communiquer a la base de donnee. pour une suppression on fait une verification supplementaire
        //1:on veut exactement un seul appel de la methode. time sert a voir la frequence d appel.

        verify(riderRepository, times(1)).findAll();
        verify(riderUtility, times(1)).tuttiRiders(riders);
    }


}
