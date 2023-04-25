package com.example.Reservas.DTO;

import com.example.Reservas.Models.Cliente;
import com.example.Reservas.Models.Habitacion;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class ReservaDTO {
    private String fechaReserva;
    private Habitacion habitacion;
    private Cliente cliente;
    private Double totalPago;

    public ReservaDTO() {
    }

    public ReservaDTO(String fechaReserva, Habitacion habitacion, Cliente cliente, Double totalPago) {
        this.fechaReserva = fechaReserva;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.totalPago = totalPago;
    }


}
