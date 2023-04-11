package com.example.Reservas.Controllers;

import com.example.Reservas.Models.Cliente;
import com.example.Reservas.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){this.clienteService=clienteService;}

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
        Cliente cliente1 = clienteService.crear(cliente);
        return ResponseEntity.ok(cliente1);
    }
}
