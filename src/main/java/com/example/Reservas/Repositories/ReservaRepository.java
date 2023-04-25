package com.example.Reservas.Repositories;

import com.example.Reservas.Models.Habitacion;
import com.example.Reservas.Models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, UUID> {

    @Query("SELECT  h FROM Habitacion h, Reserva r WHERE h.numero not in(SELECT habitacion.numero FROM Reserva) " +
            "OR h.numero NOT IN(SELECT DISTINCT r.habitacion.numero FROM  Reserva r WHERE r.fechaReserva= ?1)")
    List<Habitacion> findHabitacionByFecha(String fechaReserva);

    @Query("Select  h FROM Habitacion h, Reserva r WHERE h.numero NOT IN(SELECT habitacion.numero from Reserva) " +
            "OR h.numero NOT IN(SELECT DISTINCT r.habitacion.numero FROM  Reserva r WHERE r.fechaReserva= ?1) AND h.tipoHabitacion=?2")
    List<Habitacion> findByFechaAndTipoHabitacion(String fechaReserva, String tipoHabitacion);


    @Query("Select h.numero FROM Habitacion h, Reserva r WHERE h.numero not in" +
            "select distinct r.habitacion.numero from  Reserva r where r.fechaReserva= ?1)")
    List<Integer> findHabitacionesDisponibles(String fecha);


    @Query("SELECT r FROM Reserva r WHERE c.cedula=?1")
    List<Reserva> findByClienteCedula(Integer cedula);


}
