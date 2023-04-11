package com.example.Reservas;

import com.example.Reservas.Repositories.ClienteRepository;
import com.example.Reservas.Services.ClienteService;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class ClienteServiceTest {
    ClienteRepository clienteRepository;
    private ClienteService clienteService;


    @Before
    public void setUp(){
        this.clienteRepository = mock(ClienteRepository.class);
        this.clienteService = new ClienteService(clienteRepository);
    }


}
