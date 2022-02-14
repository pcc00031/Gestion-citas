package com.citas.servicios;

import com.citas.entidades.Cita;
import com.citas.excepciones.CitaNoCreada;
import com.citas.excepciones.CitaYaCreada;
import com.citas.repositorios.RepositorioCitas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Validated
public class ServicioCitas {
    private static final Logger log;

    // cambia el formato del logger
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        log = Logger.getLogger(ServicioCitas.class.getName());
    }

    @Autowired
    RepositorioCitas citas;

    public ServicioCitas() {
    }

    public Integer crearCita(@NotNull @Valid Cita cita) {
        log.info("Creando ... " + cita);
        if (citas.existsById(cita.getId()))
            throw new CitaYaCreada();
        return citas.save(cita).getId();
    }

    @Transactional
    public void modificarCita(int id, String descripcion, LocalDateTime fecha) {

        Optional<Cita> opCita = citas.findById(id);
        Cita c = opCita.orElseThrow(CitaNoCreada::new);
        c.setDescripcion(descripcion);
        c.setFecha(fecha);
        log.info("Modificando ... " + c);
        //citas.save(c);
    }

    public void cancelarCita(int id) {
        citas.deleteById(id);
    }

    public Optional<Cita> consultarCita(int id) {
        return citas.findById(id);
    }
}
