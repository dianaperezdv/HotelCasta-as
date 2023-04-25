package com.example.Reservas.Models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="Reserva")
public class Reserva {

    @Id
    @Column(name="codigoDeReserva")
    private UUID codigoDeReserva;
    @Column(name="fechaReserva")
    private String fechaReserva;
    @OneToOne
    @JoinColumn(name="numero")
    private Habitacion habitacion;
    @OneToMany
    @JoinColumn(name="cedula")
    private Cliente cliente;
    @Column(name="totalAPagar")
    private Double totalAPagar;

    public Reserva(){}

    public Reserva(String fechaReserva, Habitacion habitacion, Cliente cliente, Double totalAPagar) {
        this.fechaReserva = fechaReserva;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.codigoDeReserva = UUID.randomUUID();
        this.totalAPagar = totalAPagar;
    }

    public UUID getCodigoDeReserva() {
        return codigoDeReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Double getTotalAPagar() {
        return totalAPagar;
    }
}
