package com.example.Reservas.Services;

import com.example.Reservas.Models.Cliente;
import com.example.Reservas.Models.Habitacion;
import com.example.Reservas.Models.Reserva;
import com.example.Reservas.Repositories.ClienteRepository;
import com.example.Reservas.Repositories.HabitacionRepository;
import com.example.Reservas.Repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository;
    private ClienteRepository clienteRepository;
    private HabitacionRepository habitacionRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ClienteRepository clienteRepository, HabitacionRepository habitacionRepository)
    {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.habitacionRepository = habitacionRepository;
    }


    public Reserva crearReserva(Integer cedula, Integer numero, LocalDate fechaReserva){
        if(cedula < 0 || numero < 0 || fechaReserva == null ) {
            throw new RuntimeException("Los datos de la solicitud están incompletos");
        }
        //validamos que la fecha a reservar no sea al dia anterior
        LocalDate fechaActual = LocalDate.now();
        if(fechaReserva.isBefore(fechaActual)){
            throw  new RuntimeException("La fecha de reserva no puede ser anterior al día actual.");
        }


        Optional<Cliente> cliente =  this.clienteRepository.findById(cedula);
        Optional<Habitacion> habitacion =  this.habitacionRepository.findById(numero);

        //Falta validar que esté disponible la habitación (no tenga reserva para esa fecha)

        if(cliente.isPresent() && habitacion.isPresent()){
            Double totalPago = calcularTotalPago(habitacion.get());
            Reserva reserva = new Reserva(fechaReserva, habitacion.get(), cliente.get(), totalPago );
            Reserva reserva1 = this.reservaRepository.save(reserva);
            return reserva1;
        }
        else{ 
            throw new RuntimeException("El cliente o la habitación no están registrados en base de datos");
            }
    }

    public Double calcularTotalPago(Habitacion habitacion){
        Double base = habitacion.getPrecioBase();
        if (habitacion.getTipoHabitacion().equalsIgnoreCase("premium")){
            return base * 0.05 + base;
        }
        return base;
    }
//SELECT HABITACION FROM HABITACIONES JOIN COLUMN WHERE HABITACION NOT IN (SELECT HABITACIONES RESERVAS)
    public List<Reserva> reservasPorFecha (LocalDate fecha) {
        return this.reservaRepository.findByFechaReserva(fecha);
    }

    public List<Habitacion> habitacionesReservadasPorFecha(LocalDate fecha){
        List<Reserva> reservas = reservasPorFecha(fecha);
        ArrayList<Habitacion> habitacionesReservadas = new ArrayList<>();
        for (Reserva reserva:
             reservas) {
            Habitacion habitacion = reserva.getHabitacion();
            habitacionesReservadas.add(habitacion);
        }
        return habitacionesReservadas;
    }
    public List<Habitacion> habitacionesDisponibles(LocalDate fecha){
        List<Habitacion> habitacionesReservadas = habitacionesReservadasPorFecha(fecha);
        ArrayList<Integer> numeroHabitacionesReservadas = new ArrayList<>();
        for (Habitacion h:
                habitacionesReservadas) {
            Integer numero = h.getNumero();
            numeroHabitacionesReservadas.add(numero);
        }
        return habitacionRepository.findByNumeroNotIn(numeroHabitacionesReservadas);
    }

    public List<Habitacion> habitacionesDisponiblesPorTipo(LocalDate fecha, String tipo){
        List<Habitacion> habitacionesDisponibles = habitacionesDisponibles(fecha);
        return (habitacionesDisponibles.stream().filter( h -> h.getTipoHabitacion().equalsIgnoreCase(tipo))).collect(Collectors.toList());
    }

    public List<Reserva> reservasPorCliente (Integer cedula) {
        return this.reservaRepository.findByClienteCedula(cedula);
    }
}


