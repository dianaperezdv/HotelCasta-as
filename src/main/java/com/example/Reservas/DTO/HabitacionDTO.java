package com.example.Reservas.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitacionDTO {
    private Integer numero;
    private String tipoHabitacion;
    private Double precioBase;

    public HabitacionDTO(Integer numero, String tipoHabitacion, Double precioBase) {
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
        this.precioBase = precioBase;
    }

    public HabitacionDTO() {
    }

}
