package com.example.Reservas;


import com.example.Reservas.Models.Cliente;
import com.example.Reservas.Models.Habitacion;
import com.example.Reservas.Models.Reserva;
import com.example.Reservas.Repositories.ClienteRepository;
import com.example.Reservas.Repositories.HabitacionRepository;
import com.example.Reservas.Repositories.ReservaRepository;
import com.example.Reservas.Services.ReservaService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

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


    @Test(expected = RuntimeException.class)
    public void crearReservaConFechaNula(){
        //Arrange
        Integer cedula = 12;
        Integer numero = 12333;
        LocalDate fecha = null;
        //act
        Reserva reserva = this.reservaService.crearReserva(cedula, numero, fecha);
    }
    @Test(expected = RuntimeException.class)
    public void crearReservaConCedulaIncorrecta(){
        //Arrange
        Integer cedula = 0;
        Integer numero = 12333;
        LocalDate fecha = LocalDate.now();
        //act
        Reserva reserva = this.reservaService.crearReserva(cedula, numero, fecha);
    }

    @Test(expected = RuntimeException.class)
    public void crearReservaConNumeroIncorrecto(){
        //Arrange
        Integer cedula = 123;
        Integer numero = 0;
        LocalDate fecha = LocalDate.now();
        //act
        Reserva reserva = this.reservaService.crearReserva(cedula, numero, fecha);
    }

    @Test(expected = RuntimeException.class)
    public void crearReservaCuandoLaHabitacionNoExisteYElClienteExiste(){
        //Arrange
        Integer numero = 2;
        Integer cedula = 123;
        LocalDate fecha = LocalDate.of(2023,10,12);
        Cliente cliente = new Cliente(cedula,"diana","perez","calle 12 nro 2","34","papitas@gmail.com");
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        when(habitacionRepository.findById(any())).thenReturn(Optional.empty());
        //act
        Reserva reserva = this.reservaService.crearReserva(cedula, numero, fecha);
        //assert
        assertTrue(reserva.getHabitacion() == null);
        assertTrue(reserva.getFechaReserva() == null);
    }

    @Test(expected = RuntimeException.class)
    public void crearReservaCuandoHabitacionExisteYClienteNoExiste(){
        //Arrange
        Integer numero = 2;
        Integer cedula = 123;
        LocalDate fecha = LocalDate.of(2023,10,12);
        Habitacion habitacion = new Habitacion(numero, "PREMIUM", 3000.9);
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(habitacion));
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());
        //act
        Reserva reserva = this.reservaService.crearReserva(cedula, numero, fecha);
        //assert
        assertTrue(reserva.getCliente() == null);
        assertTrue(reserva.getFechaReserva() == null);

    }
    @Test(expected = RuntimeException.class)
    public void crearReservaBien(){
        //Arrange
        Integer numero = 2;
        Integer cedula = 123;
        LocalDate fecha = LocalDate.of(2023,10,12);
        Cliente cliente = new Cliente(cedula,"diana","perez","calle 12 nro 2","34","papitas@gmail.com");
        Habitacion habitacion = new Habitacion(numero, "PREMIUM", 3000.9);
        //act
        Reserva reserva = this.reservaService.crearReserva(cedula, numero, fecha);
        verify(reservaRepository, times(1)).save(any());
        assertTrue(reserva.getHabitacion().getNumero().equals(2));
        assertTrue(reserva.getFechaReserva() != null);
    }

    @Test
    public void reservasPorFecha(){
        //Arrange
        Integer numero = 2;
        Integer numero2 = 3;
        Integer cedula = 123;
        Integer cedula2 = 1234;
        LocalDate fecha = LocalDate.of(2023,10,12);
        Cliente cliente = new Cliente(cedula,"diana","perez","calle 12 nro 2","34","papitas@gmail.com");
        Habitacion habitacion = new Habitacion(numero, "PREMIUM", 3000.9);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(habitacion));
        Reserva reserva1 = this.reservaService.crearReserva(cedula, numero, fecha);
        Reserva reserva2 = this.reservaService.crearReserva(cedula2, numero2, fecha);
        // act
        List<Reserva> reservas = this.reservaRepository.findAll();
        System.out.print(reservas);
    }

    @Test
    public void reservasPorCliente(){
        //Arrange
        Integer numero = 2;
        Integer numero2 = 3;
        Integer cedula = 123;
        LocalDate fecha = LocalDate.of(2023,10,12);
        LocalDate fecha2 = LocalDate.of(2023,11,12);
        Cliente cliente = new Cliente(cedula,"diana","perez","calle 12 nro 2","34","papitas@gmail.com");
        Habitacion habitacion = new Habitacion(numero, "PREMIUM", 3000.9);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        when(habitacionRepository.findById(any())).thenReturn(Optional.of(habitacion));
        Reserva reserva1 = this.reservaService.crearReserva(cedula, numero, fecha);
        Reserva reserva2 = this.reservaService.crearReserva(cedula, numero2, fecha2);
        // act
        List<Reserva> reservas = this.reservaService.reservasPorCliente(cedula);
        System.out.print(reservas);
    }

   /* @Test
    public void agendarCitaFormatoFechaIncorrecto(){
        //TODO: Corregir para que funcione cuando la fecha esta en formato incorrecto
        //TODO:puede ser con un try catch , o pueden validarlo con una expresion regular/

        //Arrange
        Integer matricula = 12;
        Integer dni = 12333;
        String fecha = "12-10-1023";
        Paciente paciente = new Paciente("juan", "cadavid");
        Odontologo odontologo = new Odontologo("Tomas", "sanchez", 1234);
        when(pacienteRepository.findById(any())).thenReturn(Optional.of(paciente));
        when(odontologoRepository.findById(any())).thenReturn(Optional.of(odontologo));
        //act
        Turno turno = this.turnoService.asignarTurno(matricula, dni, fecha);
        //assert
        assertTrue(turno.getOdontologo() == null);
        assertTrue(turno.getFfechaTurno() == null);
    }

    @Test
    public void agendarCita(){
        //Arrange
        Integer matricula = 12;
        Integer dni = 12333;
        String fecha = "2023-12-10 10:22";
        Paciente paciente = new Paciente("juan", "cadavid");
        Odontologo odontologo = new Odontologo("Tomas", "sanchez", 1234);
        when(pacienteRepository.findById(any())).thenReturn(Optional.of(paciente));
        when(odontologoRepository.findById(any())).thenReturn(Optional.of(odontologo));
        //act
        Turno turno = this.turnoService.asignarTurno(matricula, dni, fecha);
        //assert
        verify(turnoRepository, times(1)).save(any());
        assertTrue(turno.getOdontologo().getNombre().equals("sanchez"));
        assertTrue(turno.getFfechaTurno() != null);
    }*/


}
