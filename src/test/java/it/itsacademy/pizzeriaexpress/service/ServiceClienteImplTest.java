package it.itsacademy.pizzeriaexpress.service;


import it.itsacademy.pizzeriaexpress.dto.ClienteDTO;
import it.itsacademy.pizzeriaexpress.entity.Cliente;
import it.itsacademy.pizzeriaexpress.repository.ClienteRepository;
import it.itsacademy.pizzeriaexpress.utility.ClienteUtility;
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


import static org.junit.jupiter.api.Assertions.*;//il faut importer absolument jupiter api pour tout.
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceClienteImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteUtility clienteUtility;


    @InjectMocks
    private ServiceClienteImpl serviceCliente;

    @BeforeEach
    public void innit() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistraCliente() {
        Cliente c = new Cliente();
        c.setId(1L);
        c.setNome("Matt Pokora");
        c.setIndirizzo("Via Manzini 8");
        c.setTelefono("98181981");

        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(c));
        when(clienteUtility.clienteToClienteDTO(c)).thenReturn(new ClienteDTO(1L, "Matt Pokora", "Via Manzini 8", "98181981"));
        assertEquals("Matt Pokora", serviceCliente.cerca(1L).getNome());

    }

    @Test
    public void testCercaCliente(){

        Cliente c = new Cliente();
        c.setId(1L);
        c.setNome("Matt Pokora");
        c.setIndirizzo("Via Manzini 8");
        c.setTelefono("98181981");

        ClienteDTO clienteDTO = new ClienteDTO (1L, "Matt Pokora", "Via Manzini 8", "98181981");

        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(c));
        when(clienteUtility.clienteToClienteDTO(c)).thenReturn(clienteDTO);

        ClienteDTO result = serviceCliente.cerca(1L);

        assertNotNull(result);
        assertEquals("Matt Pokora", result.getNome());
    }

    @Test
    public void testModificaCliente(){

        Long id = 1L;
        ClienteDTO inputDTO = new ClienteDTO( null, "Jiraya LeCochon","Via Valleariccia 7","87987987987");
        Cliente c = new Cliente();
        Cliente savedCliente = new Cliente();
        ClienteDTO outputDTO = new ClienteDTO(id,"Jiraya LeCochon","Via Valleariccia 7","87987987987");

        when(clienteUtility.clienteDTOToCliente(any(ClienteDTO.class))).thenReturn(c);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(savedCliente);
        when(clienteUtility.clienteToClienteDTO(any(Cliente.class))).thenReturn(outputDTO);

        ClienteDTO result = serviceCliente.modifica(id,inputDTO);

        assertNotNull(result);
        assertEquals("Jiraya LeCochon",result.getNome());
        assertEquals(id,result.getId());


    }
    @Test
    public void testCancellaCliente(){

        Long id = 1L;
        Cliente c = new Cliente();
        ClienteDTO clienteDTO = new ClienteDTO(id , "Matt Pokora", "Via Manzini 8", "87987987987");

        when(clienteRepository.findById(id)).thenReturn(java.util.Optional.of(c));
        when(clienteUtility.clienteToClienteDTO(c)).thenReturn(clienteDTO);

        ClienteDTO result = serviceCliente.cancella(id);

        assertNotNull(result);
        assertEquals(id,result.getId());

        //on verifie que l actiondu service  a ete communiquer a la base de donnee. pour une suppression on fait une verification supplementaire
        //1:on veut exactement un seul appel de la methode. time sert a voir la frequence d appel.
        verify(clienteRepository, times(1)).deleteById(id);

    }

    @Test
    public void testTuttiIClienti() {

        // Création de la première entité réelle
        Cliente c1 = new Cliente();
        c1.setId(1L);
        c1.setNome("Matt Pokora");
        c1.setIndirizzo("Via Manzini 8");
        c1.setTelefono("98181981");

        // Création de la deuxième entité réelle
        Cliente c2 = new Cliente();
        c2.setId(2L);
        c2.setNome("Jiraya Lecochon");
        c2.setIndirizzo("Via Valleariccia 7");
        c2.setTelefono("87987987987");

        // Liste d'entités pour le repository
        List<Cliente> clienti = Arrays.asList(c1, c2);

        // On crée les DTO correspondants (ce que le mapper doit renvoyer)
        Collection<ClienteDTO> clientiDTO = Arrays.asList(
                new ClienteDTO(1L, "Matt Pokora", "Via Manzini 8", "98181981"),
                new ClienteDTO(2L,"Jiraya Lecochon","Via Valleariccia 7", "87987987987")
        );

        when(clienteRepository.findAll()).thenReturn(clienti);
        when(clienteUtility.tuttiClienti(clienti)).thenReturn(clientiDTO);


        Collection<ClienteDTO> result = serviceCliente.tuttiClienti();

        assertNotNull(result);
        assertEquals(2, result.size(), "la collection deve contenere 2 Clienti");

        // Vérification des appels
        //on verifie que l actiondu service  a ete communiquer a la base de donnee. pour une suppression on fait une verification supplementaire
        //1:on veut exactement un seul appel de la methode. time sert a voir la frequence d appel.

        verify(clienteRepository, times(1)).findAll();
        verify(clienteUtility, times(1)).tuttiClienti(clienti);
    }


}
