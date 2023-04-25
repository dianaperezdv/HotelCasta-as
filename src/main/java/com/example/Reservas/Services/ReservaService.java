package com.example.Reservas.Services;

import com.example.Reservas.DTO.ReservaDTO;
import com.example.Reservas.Exception.ApiRequestException;
import com.example.Reservas.Models.Cliente;
import com.example.Reservas.Models.Habitacion;
import com.example.Reservas.Models.Reserva;
import com.example.Reservas.Repositories.ClienteRepository;
import com.example.Reservas.Repositories.HabitacionRepository;
import com.example.Reservas.Repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public ReservaDTO crearReserva(Integer cedula, Integer numero, String fechaReserva){
        if(cedula < 0 || numero < 0 || fechaReserva == null ) {
            throw new ApiRequestException("Los datos de la solicitud están incompletos");
        }
        //Validamos que la fecha tenga el formato correcto
        Pattern pattern = Pattern
                .compile("^\\d{4}-\\d{2}-\\d{2}$");
        Matcher matcher = pattern.matcher(fechaReserva);
        if(!matcher.find()){
            throw new ApiRequestException("La fecha debe ser yyyy-MM-dd");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fechaReserva, formatter);
        //Validamos que la fecha no sea anterior a la actual
        if(date.isBefore(LocalDate.now())){
            throw new ApiRequestException("La fecha no puede ser anterior a la actual");
        }
        //Validamos que el cliente y la habitación existan en base de datos
        Optional<Cliente> cliente =  this.clienteRepository.findById(cedula);
        Optional<Habitacion> habitacion =  this.habitacionRepository.findById(numero);
        if(cliente.isPresent() && habitacion.isPresent()){
            //Validamos que la habitación esté disponible en la fecha indicada
            List<Integer> habitacionesDisponibles = this.reservaRepository.findHabitacionesDisponibles(fechaReserva);
            if(!habitacionesDisponibles.contains(numero)){
                throw new ApiRequestException("La habitación no está disponible en la fecha indicada");
            }
            //Creamos la reserva
            Double totalPago = calcularTotalPago(habitacion.get());
            Reserva reserva1 = this.reservaRepository.save(new Reserva(fechaReserva, habitacion.get(), cliente.get(), totalPago ));
            //Devolvemos el DTO
            return new ReservaDTO(reserva1.getFechaReserva(),reserva1.getHabitacion(), reserva1.getCliente(), reserva1.getTotalAPagar());
        }
        else{ 
            throw new ApiRequestException("El cliente o la habitación no están registrados en base de datos");
            }
    }

    public Double calcularTotalPago(Habitacion habitacion){
        Double base = habitacion.getPrecioBase();
        if (habitacion.getTipoHabitacion().equalsIgnoreCase("premium")){
            return base * 0.05 + base;
        }
        return base;
    }

    public List<Habitacion> habitacionesDisponiblesPorFecha(String fecha){
            return this.reservaRepository.findHabitacionByFecha(fecha);
    }

    public List<Habitacion> habitacionesPorFechaYTipo(String fecha, String tipo){
        return this.reservaRepository.findByFechaAndTipoHabitacion(fecha,tipo);
    }

    public List<Reserva> reservasPorCliente (Integer cedula) {
        return this.reservaRepository.findByClienteCedula(cedula);
    }
}


