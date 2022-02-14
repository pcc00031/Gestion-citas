package com.citas.entidades;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CitaTest {

    @Test
    public void citaTest() {
        Cita c = new Cita();
        String descripcion = "Cita acelerada";
        Integer id = 1;
        LocalDateTime fecha = LocalDateTime.now();
        c.setFecha(fecha);
        c.setId(id);
        c.setDescripcion(descripcion);
        Assertions.assertThat(id).isEqualTo(c.getId());
        Assertions.assertThat(fecha).isEqualTo(c.getFecha());
        Assertions.assertThat(descripcion).isEqualTo(c.getDescripcion());
    }
}
