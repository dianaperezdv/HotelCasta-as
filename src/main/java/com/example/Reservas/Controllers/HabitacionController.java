package com.example.Reservas.Controllers;

import com.example.Reservas.DTO.ClienteDTO;
import com.example.Reservas.DTO.HabitacionDTO;
import com.example.Reservas.Models.Habitacion;
import com.example.Reservas.Services.HabitacionService;
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
public class HabitacionController {

    private final HabitacionService habitacionService;
    private final ReservaService reservaService;
    @Autowired
    public HabitacionController(HabitacionService habitacionService, ReservaService reservaService){
        this.habitacionService=habitacionService;
        this.reservaService = reservaService;}

    @ApiOperation(value="Crear una nueva habitación", response = HabitacionDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Habitación creada con exito", response = HabitacionDTO.class),
            @ApiResponse(code = 404, message = "No existe la habitación", response = Exception.class),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    @PostMapping("/habitacion")
    @PreAuthorize("hasRole('WRITE')")
    public HabitacionDTO crearHabitacion(@RequestBody HabitacionDTO habitacionDTO){
        return habitacionService.crear(habitacionDTO);
    }

    @GetMapping("/habitaciones")
    @PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Obtener lista de las habitaciones disponibles para reservar en la fecha indicada", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Lista de habitaciones  obtenida exitosamente", response = List.class),
            @ApiResponse(code = 401, message = "No autorizado", response = List.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = List.class)

    })
    public List<Habitacion> HabitacionesDisponiblesFecha(@RequestParam("fecha") String fecha){
        return reservaService.habitacionesDisponiblesPorFecha(fecha);
    }

    @GetMapping("/habitaciones")
    @PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Obtener lista de las habitaciones disponibles para reservar en la fecha indicada según un tipo específico de habitación", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Lista de habitaciones obtenida exitosamente", response = List.class),
            @ApiResponse(code = 401, message = "No autorizado", response = List.class),
            @ApiResponse(code = 500, message = "Error interno del servidor", response = List.class)

    })
    public List<Habitacion> habitacionesDisponiblesTipo(@RequestParam("fecha") String fecha, @RequestParam("tipo") String tipo){
        return reservaService.habitacionesPorFechaYTipo(fecha, tipo);
    }
}
