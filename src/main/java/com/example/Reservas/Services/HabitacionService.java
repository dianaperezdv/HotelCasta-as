package com.example.Reservas.Services;

import com.example.Reservas.Models.Habitacion;
import com.example.Reservas.Repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;

    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository){ this.habitacionRepository = habitacionRepository;}

    public Habitacion crear(Habitacion habitacion){
        return habitacionRepository.save(habitacion);
    }

   // public List<Habitacion> buscarHabitacionesDisponiblesFecha(LocalDate fecha){
     //   return habitacionRepository.findByFechaReserva(fecha);
    //}

    // uno para traer todas las habitaciones disponibles(que no tengan reserva) por fecha
    /*public List<Habitacion> disponiblesPorFecha(LocalDate fecha){
        List<Reseva> reservas = reservaRepository.findByFechaReserva(fecha);
        List<OtraLista> numeroHabitacion = reservas.stream()
                .map(Reserva::getHabitacion)
                .collect(Collectors.toList());

        //Buscar en las reservas la fecha - método dentro de reservas
        // Retornar las habitaciones que no están registradas en esas reservas
        //


    }


    // otro para traer todas las habitaciones disponibles(que no tengan reserva)
    // pero filtradas por el tipo de habitación, use queryParameters.

     */
}
