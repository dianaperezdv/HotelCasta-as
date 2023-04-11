package com.example.Reservas.Repositories;

import com.example.Reservas.Models.Habitacion;
import com.example.Reservas.Models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, UUID> {

    @Query("SELECT r FROM Reserva r WHERE r.fechaReserva=?1")
    List<Reserva> findByFechaReserva (LocalDate fechaReserva);

    @Query("SELECT h FROM Reserva r WHERE h.numero=?1")
    List<Habitacion> findByHabitacionNumero(Integer numero);
    @Query("SELECT c FROM Reserva r WHERE c.cedula=?1")
    List<Reserva> findByClienteCedula(Integer cedula);


}
