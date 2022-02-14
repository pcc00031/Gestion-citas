package com.citas.servicios;

import com.citas.entidades.Cita;
import com.citas.excepciones.CitaNoCreada;
import com.citas.excepciones.CitaYaCreada;
import com.citas.repositorios.RepositorioCitas;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootTest(classes = com.citas.app.GestionCitasApplication.class)
public class ServicioCitasTest {
    @Autowired
    RepositorioCitas citas;

    @Autowired
    ServicioCitas servicio;

    Cita crearCita() {
        Cita c = new Cita();
        String descripcion = "Cita acelerada";
        LocalDateTime fecha = LocalDateTime.now().plusHours(1);
        c.setFecha(fecha);
        c.setDescripcion(descripcion);
        return c;
    }

    @Test
    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    public void testCrearCita() {
        Cita c = crearCita();
        Assertions.assertThat(citas.count()).isEqualTo(0);
        servicio.crearCita(c);
        Assertions.assertThat(citas.count()).isEqualTo(1);
        Assertions.assertThat(citas.existsById(1));
    }

    @Test
    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    public void testCrearCitaInvalida() {
        Cita c = crearCita();
        Cita c2 = crearCita();
        servicio.crearCita(c);
        servicio.crearCita(c2);

        Assertions.assertThat(citas.count()).isEqualTo(2);

        Assertions.assertThatThrownBy(() -> {
            servicio.crearCita(c2);
        }).isInstanceOf(CitaYaCreada.class);
    }

    @Test
    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    public void testModificarCita() {
        Cita c = crearCita();
        servicio.crearCita(c);
        String descripcion = "Cita desacelerada";
        LocalDateTime fecha = LocalDateTime.now().plusMonths(1);
        servicio.modificarCita(c.getId(), descripcion, fecha);

        Assertions.assertThat(citas.findById(c.getId()).orElseThrow(CitaNoCreada::new).getDescripcion()).isEqualTo(descripcion);
        Assertions.assertThat(citas.findById(c.getId()).orElseThrow(CitaNoCreada::new).getFecha()).
                isCloseTo(fecha, Assertions.byLessThan(1, ChronoUnit.SECONDS));
    }

    @Test
    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    public void testCancelarCita() {
        Cita c = crearCita();
        servicio.crearCita(c);
        Assertions.assertThat(citas.count()).isEqualTo(1);
        servicio.cancelarCita(c.getId());
        Assertions.assertThat(citas.count()).isEqualTo(0);
    }
}
