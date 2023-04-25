package com.example.Reservas.Controllers;

import com.example.Reservas.DTO.HabitacionDTO;
import com.example.Reservas.DTO.ReservaDTO;
import com.example.Reservas.DTO.SolicitudReservaDTO;
import com.example.Reservas.Models.Reserva;
import com.example.Reservas.Services.ReservaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReservaController {

    private final ReservaService reservaService;
    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/reserva")
    @PreAuthorize("hasRole('WRITE')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reserva creada con exito", response = ReservaDTO.class),
            @ApiResponse(code = 404, message = "No existe la reserva", response = Exception.class),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    public ReservaDTO generarReserva(@RequestBody SolicitudReservaDTO reserva){
        Integer cedula = reserva.getCedula();
        Integer numero = reserva.getNumero();
        String fecha = reserva.getFecha();
        return this.reservaService.crearReserva(cedula,numero,fecha);
    }

    @GetMapping("/reservas/{cedula}")
    @PreAuthorize("hasRole('READ')")
    @ApiOperation(value="Obtener todas las reservas de un cliente específico", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de reservas obtenida con exito", response = List.class),
            @ApiResponse(code = 404, message = "No existen recursos", response = Exception.class),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    public List<Reserva> reservasPorCliente(@PathVariable Integer cedula){
       return this.reservaService.reservasPorCliente(cedula);
    }

}
