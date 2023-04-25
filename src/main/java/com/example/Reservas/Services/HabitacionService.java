package com.example.Reservas.Services;

import com.example.Reservas.DTO.HabitacionDTO;
import com.example.Reservas.Exception.ApiRequestException;
import com.example.Reservas.Models.Habitacion;
import com.example.Reservas.Repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;

    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository){ this.habitacionRepository = habitacionRepository;}

    public HabitacionDTO crear(HabitacionDTO habitacionDTO){
        if(habitacionDTO.getTipoHabitacion()==null){
            throw new ApiRequestException("Es necesario el tipo de habitacion, debe ser estándar o premium");
        } else if (habitacionDTO.getPrecioBase()==null) {
            throw new ApiRequestException("Falta el precio base de la habitación");
        } else if (habitacionDTO.getNumero()==null) {
            throw new ApiRequestException("Falta el número de la habitacion");
        }
        Habitacion habitacion = new Habitacion(habitacionDTO.getNumero(), habitacionDTO.getTipoHabitacion(), habitacionDTO.getPrecioBase());
        this.habitacionRepository.save(habitacion);
        return  habitacionDTO;
    }


}
