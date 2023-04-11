package com.example.Reservas.Services;

import com.example.Reservas.Models.Cliente;
import com.example.Reservas.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente crear (Cliente cliente){
        if (cliente.getNombre()==null || cliente.getApellido() == null){
            throw new RuntimeException("Los campos nombre y apellido son obligatorios");
        }
        clienteRepository.save(cliente);

        return cliente;
    }
}
