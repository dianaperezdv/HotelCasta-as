package com.example.Reservas;

import com.example.Reservas.DTO.ClienteDTO;
import com.example.Reservas.Exception.ApiRequestException;
import com.example.Reservas.Repositories.ClienteRepository;
import com.example.Reservas.Services.ClienteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.mock;

public class ClienteServiceTest {
    ClienteRepository clienteRepository;
    private ClienteService clienteService;


    @Before
    public void setUp(){
        this.clienteRepository = mock(ClienteRepository.class);
        this.clienteService = new ClienteService(clienteRepository);
    }

    @Test
    public void crearClienteConNombreNull() throws ApiRequestException{
        //Arrange
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre(null);
        clienteDTO.setApellido("Perez");
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            clienteService.crear(clienteDTO);
        });

    }
    @Test
    public void testCrearClienteConApellidoNull() throws ApiRequestException{
        //Arrange
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Diana");
        clienteDTO.setApellido(null);
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            clienteService.crear(clienteDTO);
        });
    }

    @Test
    public void crearClienteConCedulaNull() throws ApiRequestException{
        //Arrange
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Diana");
        clienteDTO.setApellido("Perez");
        clienteDTO.setCedula(null);
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            clienteService.crear(clienteDTO);
        });

    }
    @Test
    public void crearClienteConNombreyApellidoNull() throws ApiRequestException{
        //Arrange
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre(null);
        clienteDTO.setApellido(null);
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            clienteService.crear(clienteDTO);
        });
    }

    @Test
    public void crearClienteConNombreYApellidoVacio() throws ApiRequestException{
        //Arrange
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("");
        clienteDTO.setApellido("");
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            clienteService.crear(clienteDTO);
        });
    }

    @Test
    public void crearClienteConNombreYCedulaNull() throws ApiRequestException{
        //Arrange
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre(null);
        clienteDTO.setCedula(null);
        clienteDTO.setApellido("Perez");
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            clienteService.crear(clienteDTO);
        });
    }
    @Test
    public void crearClienteExitoso(){
        //Arrange
        ClienteDTO clienteDTO = new ClienteDTO(123, "Diana","Perez","Calle 123 #1-62", "2", "dianita@gmail.com");
        //act
        ClienteDTO result =  clienteService.crear(clienteDTO);
        //assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(123, result.getCedula());
        Assertions.assertEquals("Diana", result.getNombre());
        Assertions.assertEquals("Perez", result.getApellido());
        Assertions.assertEquals("Calle 123 #1-62", result.getDireccion());
        Assertions.assertEquals("2", result.getEdad());
        Assertions.assertEquals("dianita@gmail.com", result.getEmail());

    }

}
