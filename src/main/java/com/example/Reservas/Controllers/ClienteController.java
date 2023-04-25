package com.example.Reservas.Controllers;

import com.example.Reservas.DTO.ClienteDTO;
import com.example.Reservas.Services.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @ApiOperation(value="Crear un nuevo cliente", response = ClienteDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente creado con exito", response = ClienteDTO.class),
            @ApiResponse(code = 404, message = "No existe el cliente", response = Exception.class),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    @PostMapping("/cliente")
    @PreAuthorize("hasRole('WRITE')")
    public ClienteDTO crear(@RequestBody ClienteDTO cliente){
        return clienteService.crear(cliente);
    }
}
