package com.example.Reservas;

import com.example.Reservas.Repositories.HabitacionRepository;
import com.example.Reservas.Services.HabitacionService;
import com.example.Reservas.Services.ReservaService;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class HabitacionServiceTest {
    HabitacionRepository habitacionRepository;
    HabitacionService habitacionService;


    @Before
    public void setUp(){
        this.habitacionRepository = mock(HabitacionRepository.class);
        this.habitacionService = new HabitacionService(habitacionRepository);
    }

}
