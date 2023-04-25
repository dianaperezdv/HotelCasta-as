package com.example.Reservas;

import com.example.Reservas.DTO.ReservaDTO;
import com.example.Reservas.Exception.ApiRequestException;
import com.example.Reservas.Models.Cliente;
import com.example.Reservas.Models.Habitacion;
import com.example.Reservas.Models.Reserva;
import com.example.Reservas.Repositories.ClienteRepository;
import com.example.Reservas.Repositories.HabitacionRepository;
import com.example.Reservas.Repositories.ReservaRepository;
import com.example.Reservas.Services.ReservaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReservaServiceTest {

    ReservaRepository reservaRepository;
    HabitacionRepository habitacionRepository;
    ClienteRepository clienteRepository;
    ReservaService reservaService;


    @Before
    public void setUp(){
        this.reservaRepository = mock(ReservaRepository.class);
        this.habitacionRepository = mock(HabitacionRepository.class);
        this.clienteRepository = mock(ClienteRepository.class);
        this.reservaService = new ReservaService(reservaRepository, clienteRepository, habitacionRepository);
    }

    @Test
    public void crearReservaBien(){
        //Arrange
        Integer numero = 2;
        Integer cedula = 123;
        String fecha = ("2023-12-12");
        Cliente cliente = new Cliente(cedula,"diana","perez","calle 12 nro 2","34","papitas@gmail.com");
        Habitacion habitacion = new Habitacion(numero, "PREMIUM", 3000.9);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(habitacion));
        when(reservaRepository.findHabitacionesDisponibles(fecha)).thenReturn(List.of(numero));
        double totalPago = 3000.9;
        when(reservaRepository.save(any())).thenReturn(new Reserva(fecha, habitacion, cliente, totalPago));
        //act
        ReservaDTO reserva = this.reservaService.crearReserva(cedula, numero, fecha);
        //assert
        verify(this.reservaRepository, times(1)).save(any());
        assertTrue(reserva.getHabitacion().getNumero().equals(2));
        assertTrue(reserva.getFechaReserva() != null);
    }

    @Test
    public void crearReservaConFechaNula() throws ApiRequestException{
        //Arrange
        Integer cedula = 12;
        Integer numero = 12333;
        String fecha = null;
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            reservaService.crearReserva(cedula, numero, fecha);
        });
    }

    @Test
    public void crearReservaConFechaAnteriorALaActual() throws ApiRequestException{
        //Arrange
        Integer cedula = 12;
        Integer numero = 12333;
        String fecha = ("2020-10-12");
        //act and assert
        ApiRequestException exception = Assertions.assertThrows(ApiRequestException.class, () -> {
            reservaService.crearReserva(cedula, numero, fecha);
        });
        assertTrue(exception.getMessage().contains("La fecha no puede ser anterior a la actual"));
    }
    @Test
    public void crearReservaConCedulaIncorrecta() throws ApiRequestException {
        //Arrange
        Integer cedula = 0;
        Integer numero = 12333;
        String fecha = ("2023-10-12");
        //act and assert
        Assertions.assertThrows(ApiRequestException.class, () -> {
            reservaService.crearReserva(cedula, numero, fecha);
        });
    }

    @Test
    public void crearReservaConNumeroIncorrecto() throws ApiRequestException {
        //Arrange
        Integer cedula = 123;
        Integer numero = 0;
        String fecha = ("2023-10-12");
        //act and assert

        Assertions.assertThrows(ApiRequestException.class, () -> {
            reservaService.crearReserva(cedula, numero, fecha.toString());
        });
    }

    @Test
    public void crearClienteConFechaFormatoIncorrecto(){
        //Arrange
        Integer cedula = 123;
        Integer numero = 12333;
        String fecha = ("10-12-2023");
        //act and assert
        ApiRequestException exception = Assertions.assertThrows(ApiRequestException.class, () -> {
            reservaService.crearReserva(cedula, numero, fecha);
        });
        assertTrue(exception.getMessage().contains("La fecha debe ser yyyy-MM-dd"));
    }
    @Test
    public void crearReservaCuandoLaHabitacionNoExisteYElClienteExiste() throws ApiRequestException {
        //Arrange
        Integer numero = 2;
        Integer cedula = 123;
        String fecha = ("2023-10-12");
        Cliente cliente = new Cliente(cedula,"diana","perez","calle 12 nro 2","34","papitas@gmail.com");
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        when(habitacionRepository.findById(any())).thenReturn(Optional.empty());
        //act
        ApiRequestException exception = Assertions.assertThrows(ApiRequestException.class, () -> {
            reservaService.crearReserva(cedula, numero, fecha);
        });
        //assert
        assertTrue(exception.getMessage().contains("El cliente o la habitación no están registrados en base de datos"));
    }

    @Test
    public void crearReservaCuandoHabitacionExisteYClienteNoExiste() throws ApiRequestException {
        //Arrange
        Integer numero = 2;
        Integer cedula = 123;
        String fecha = ("2023-10-12");
        Habitacion habitacion = new Habitacion(numero, "PREMIUM", 3000.9);
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(habitacion));
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());
        //Act
        ApiRequestException exception = Assertions.assertThrows(ApiRequestException.class, () -> {
            reservaService.crearReserva(cedula, numero, fecha);
        });
        //assert
        assertTrue(exception.getMessage().contains("El cliente o la habitación no están registrados en base de datos"));
        Assertions.assertThrows(ApiRequestException.class, () -> {
            reservaService.crearReserva(cedula, numero, fecha);
        });
    }

}
