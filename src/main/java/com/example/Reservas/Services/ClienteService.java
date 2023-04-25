package com.example.Reservas.Services;

import com.example.Reservas.DTO.ClienteDTO;
import com.example.Reservas.Exception.ApiRequestException;
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

    public ClienteDTO crear(ClienteDTO clienteDTO){

        if(clienteDTO.getCedula() == null || clienteDTO.getApellido() == null || clienteDTO.getNombre() == null){
            throw new ApiRequestException("la cedula, el apellido o el nombre es invalidos");
        }

        Cliente cliente = new Cliente(
                clienteDTO.getCedula(),
                clienteDTO.getNombre(),
                clienteDTO.getApellido(),
                clienteDTO.getDireccion(),
                clienteDTO.getEdad(),
                clienteDTO.getEmail()
        );
        clienteRepository.save(cliente);
        return clienteDTO;
    }
}
