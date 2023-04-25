package com.example.Reservas.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SolicitudReservaDTO {
    Integer cedula;
    Integer numero;
    String fecha;

    public SolicitudReservaDTO(Integer cedula, Integer numero, String fecha) {
        this.cedula = cedula;
        this.numero = numero;
        this.fecha = fecha;
    }
    public SolicitudReservaDTO() {}
}
