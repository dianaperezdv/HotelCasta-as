package com.example.Reservas;

import com.example.Reservas.DTO.HabitacionDTO;
import com.example.Reservas.Exception.ApiRequestException;
import com.example.Reservas.Models.Habitacion;
import com.example.Reservas.Repositories.HabitacionRepository;
import com.example.Reservas.Services.HabitacionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.*;

public class HabitacionServiceTest {
    HabitacionRepository habitacionRepository;
    HabitacionService habitacionService;


    @Before
    public void setUp(){
        this.habitacionRepository = mock(HabitacionRepository.class);
        this.habitacionService = new HabitacionService(habitacionRepository);
    }
    @Test
    public void crearHabitacionConNumeroNull() throws ApiRequestException{
        //Arrange
        HabitacionDTO habitacionDTO = new HabitacionDTO();
        habitacionDTO.setNumero(null);
        habitacionDTO.setTipoHabitacion("PREMIUM");
        habitacionDTO.setPrecioBase(Double.valueOf(1000));
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            habitacionService.crear(habitacionDTO);
        });
    }
    @Test
    public void crearHabitacionConTipoNull() throws ApiRequestException{
        //Arrange
        HabitacionDTO habitacionDTO = new HabitacionDTO();
        habitacionDTO.setNumero(1);
        habitacionDTO.setTipoHabitacion(null);
        habitacionDTO.setPrecioBase(Double.valueOf(1000));
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            habitacionService.crear(habitacionDTO);
        });
    }
    @Test
    public void crearHabitacionConPrecioNull() throws ApiRequestException{
        //Arrange
        HabitacionDTO habitacionDTO = new HabitacionDTO();
        habitacionDTO.setNumero(1);
        habitacionDTO.setTipoHabitacion("PREMIUM");
        habitacionDTO.setPrecioBase(null);
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            habitacionService.crear(habitacionDTO);
        });
    }
    @Test
    public void crearHabitacionConTipoYPrecioNull() throws ApiRequestException {
        //Arrange
        HabitacionDTO habitacionDTO = new HabitacionDTO();
        habitacionDTO.setPrecioBase(null);
        habitacionDTO.setNumero(1);
        habitacionDTO.setTipoHabitacion(null);
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            habitacionService.crear(habitacionDTO);
        });

    }
    @Test
    public void crearHabitacionConNumeroYTipoNull() throws ApiRequestException{
        //Arrange
        HabitacionDTO habitacionDTO = new HabitacionDTO();
        habitacionDTO.setPrecioBase(Double.valueOf(1000));
        habitacionDTO.setNumero(null);
        habitacionDTO.setTipoHabitacion(null);
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            habitacionService.crear(habitacionDTO);
        });
    }

    @Test
    public void crearHabitacionConNumeroYPrecioNull() throws ApiRequestException{
        //Arrange
        HabitacionDTO habitacionDTO = new HabitacionDTO();
        habitacionDTO.setPrecioBase(null);
        habitacionDTO.setNumero(null);
        habitacionDTO.setTipoHabitacion("PREMIUM");
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            habitacionService.crear(habitacionDTO);
        });
    }
    @Test
    public void crearHabitacionExitoso(){
        //Arrange
        HabitacionDTO habitacionDTO = new HabitacionDTO();
        habitacionDTO.setNumero(1);
        habitacionDTO.setTipoHabitacion("PREMIUM");
        habitacionDTO.setPrecioBase(Double.valueOf(1000));
        //act
        HabitacionDTO habitacion = this.habitacionService.crear(habitacionDTO);
        //assert
        verify(habitacionRepository, times(1)).save(any(Habitacion.class));
        Assertions.assertEquals(habitacionDTO.getNumero(), habitacion.getNumero());
    }
}
