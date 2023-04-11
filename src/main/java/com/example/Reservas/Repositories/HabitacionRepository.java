package com.example.Reservas.Repositories;

import com.example.Reservas.Models.Habitacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HabitacionRepository extends CrudRepository<Habitacion, Integer> {

    List<Habitacion> findByNumeroNotIn(List<Integer> numerosHabitaciones);

    @Query("Select h.numero FROM Habitacion h, Reserva r WHERE h.numero not in(select distinct r.habitacion.numero from  Reserva r where r.fechaReserva= ?1)")
    List<Habitacion> findAvailableRooms(LocalDate fecha);

}
