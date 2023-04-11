package com.example.Reservas.Controllers;

import com.example.Reservas.Models.Reserva;
import com.example.Reservas.Services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public Reserva generarReserva(@RequestBody Reserva reserva){
        Integer cedula = reserva.getCliente().getCedula();
        Integer numero = reserva.getHabitacion().getNumero();
        LocalDate fecha = reserva.getFechaReserva();
        return this.reservaService.crearReserva(cedula,numero,fecha);
    }

    @GetMapping("/reservas/{cedula}")
    public List<Reserva> reservasPorCliente(@PathVariable Integer cedula){
       return this.reservaService.reservasPorCliente(cedula);
    }
}
