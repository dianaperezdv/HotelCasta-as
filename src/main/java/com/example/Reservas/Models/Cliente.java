package com.example.Reservas.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cliente")
public class Cliente {

    @Id
    @Column(name="cedula")
    private Integer cedula;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="direccion")
    private String direccion;
    @Column(name="edad")
    private String edad;
    @Column(name="correoElectronico")
    private String correoElectronico;

    public Cliente(){

    }
    public Cliente(Integer cedula, String nombre, String apellido, String direccion, String edad, String correoElectronico) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.edad = edad;
        this.correoElectronico = correoElectronico;
    }

    public Integer getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEdad() {
        return edad;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
}
