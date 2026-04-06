package com.donaton.msdonaciones.factory;

import  com.donaton.msdonaciones.model.Donacion;
import org.springframework.stereotype.Component;    
import java.time.LocalDate;
@Component
public class DonacionFactory {
    public Donacion crearDonacion(String tipo, int cantidad, String origen, String centro) {
        Donacion d = new Donacion();
        d.setTipoDonacion(tipo);
        d.setCantidad(cantidad);
        d.setOrigen(origen);
        d.setFecha(LocalDate.now());
        d.setCentroAcopio(centro);
        return d;
    }
}