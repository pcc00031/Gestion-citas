package com.citas.controladorREST.DTO;

import com.citas.entidades.Cita;

import java.time.LocalDateTime;

public class DTOCita {
    String descripcion;
    LocalDateTime fecha;

    public DTOCita() {
    }

    public DTOCita(Cita cita) {
        this.descripcion = cita.getDescripcion();
        this.fecha = cita.getFecha();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Cita aCita() {
        Cita c = new Cita();
        c.setFecha(this.fecha);
        c.setDescripcion(this.descripcion);
        return c;
    }
}
