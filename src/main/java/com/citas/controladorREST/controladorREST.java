package com.citas.controladorREST;

import com.citas.controladorREST.DTO.DTOCita;
import com.citas.entidades.Cita;
import com.citas.excepciones.CitaYaCreada;
import com.citas.servicios.ServicioCitas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class controladorREST {
    @Autowired
    ServicioCitas servicio;

    @PostMapping("citas")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Integer> crearCita(@RequestBody DTOCita cita) {
        try {
            Integer id = servicio.crearCita(cita.aCita());
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (CitaYaCreada e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("citas/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<DTOCita> consultarCita(@PathVariable Integer id) {
        Optional<Cita> cita = servicio.consultarCita(id);
        return cita.map(c -> ResponseEntity.ok(new DTOCita(c)))
                .orElse(ResponseEntity.notFound().build());

    }
}
