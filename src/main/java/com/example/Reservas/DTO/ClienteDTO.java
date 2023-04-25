package com.example.Reservas.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private Integer cedula;
    private String nombre;
    private String apellido;

    private String direccion;
    private String edad;
    private String email;

    public ClienteDTO(Integer cedula, String nombre, String apellido, String direccion, String edad, String email) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.edad= edad;
        this.email = email;
    }

    public ClienteDTO(){}

}
