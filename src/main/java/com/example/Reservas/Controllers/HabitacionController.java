package com.example.Reservas.Controllers;

import com.example.Reservas.Models.Habitacion;
import com.example.Reservas.Services.HabitacionService;
import com.example.Reservas.Services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/habitacion")
    public ResponseEntity crearHabitacion(@RequestBody Habitacion habitacion){
        return ResponseEntity.ok(habitacionService.crear(habitacion));
    }

    @GetMapping("/habitaciones")
    public List<Habitacion> HabitacionesDisponiblesFecha(@RequestParam("fecha") LocalDate fecha){
        return reservaService.habitacionesDisponibles(fecha);
    }

    @GetMapping("/habitaciones/{tipo}")
    public List<Habitacion> habitacionesDisponiblesTipo(@PathVariable String tipo, @RequestParam("fecha") LocalDate fecha){
        return reservaService.habitacionesDisponiblesPorTipo(fecha, tipo);
    }
}
