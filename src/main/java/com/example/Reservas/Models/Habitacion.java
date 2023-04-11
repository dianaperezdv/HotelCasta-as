package com.example.Reservas.Models;

import javax.persistence.*;

@Entity
@Table(name="Habitacion")
public class Habitacion {

    @Id
    @Column(name="numero")
    private Integer numero;
    @Column(name="tipoHabitacion")
    private String tipoHabitacion;
    @Column(name="precioBase")
    private Double precioBase;

    public Habitacion(){

    }

    public Habitacion(Integer numero, String tipoHabitacion, Double precioBase) {
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
        this.precioBase = precioBase;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }
    public Double getPrecioBase(){return precioBase;}


}
