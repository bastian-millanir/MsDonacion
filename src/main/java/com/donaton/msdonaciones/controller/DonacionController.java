package com.donaton.msdonaciones.controller;

import com.donaton.msdonaciones.model.Donacion;
import com.donaton.msdonaciones.service.DonacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/donaciones")
@RequiredArgsConstructor
public class DonacionController {

    private final DonacionService service;

    @GetMapping
    public List<Donacion> listar() {
        return service.listar();
    }

    @PostMapping
    public Donacion crear(@RequestBody Donacion donacion) {
        return service.crear(
            donacion.getTipoDonacion(),
            donacion.getCantidad(),
            donacion.getOrigen(),
            donacion.getCentroAcopio()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donacion> obtener(@PathVariable Long id) {
        return service.listar().stream()
            .filter(d -> d.getId().equals(id))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
