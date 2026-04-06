package com.donaton.msdonaciones.service;

import com.donaton.msdonaciones.model.Donacion;
import com.donaton.msdonaciones.repository.DonacionRepository;
import com.donaton.msdonaciones.factory.DonacionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@RequiredArgsConstructor
public class DonacionService {

    private final DonacionRepository repository;
    private final DonacionFactory factory;

    public List<Donacion> listar() {
        return repository.findAll();
    }

    public Donacion crear(String tipo, int cantidad, String origen, String centro) {
        Donacion d = factory.crearDonacion(tipo, cantidad, origen, centro);
        return repository.save(d);
    }

    // Circuit Breaker: si falla, retorna lista vacía en vez de error
    @CircuitBreaker(name = "donacionService", fallbackMethod = "fallbackListar")
    public List<Donacion> listarConCircuitBreaker() {
        return repository.findAll();
    }

    public List<Donacion> fallbackListar(Exception e) {
        return new ArrayList<>();
    }
}
