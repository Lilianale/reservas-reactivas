package com.aerolinea.reservas.controller;

import com.aerolinea.reservas.model.ReservationEvent;
import com.aerolinea.reservas.util.ReservationFilters;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ReservationEvent> getReservationStream() {

        // 5 Reservas en memoria (3 válidas, 2 inválidas)
        ReservationEvent r1 = new ReservationEvent("1", "Carlos Gomez", 250.0, List.of("carlos@mail.com"));
        ReservationEvent r2 = new ReservationEvent("2", "Ana Lopez", -50.0, List.of("ana@mail.com")); // Inválida (Precio negativo)
        ReservationEvent r3 = new ReservationEvent("3", "Luis Martinez", 180.0, List.of("luis@mail.com"));
        ReservationEvent r4 = new ReservationEvent("4", "Maria Rodriguez", 300.0, List.of()); // Inválida (Sin emails)
        ReservationEvent r5 = new ReservationEvent("5", "Elena Perez", 420.0, List.of("elena@mail.com"));

        // Reserva genérica por defecto en caso de que todo el flujo quede vacío
        ReservationEvent defaultReservation = new ReservationEvent("0", "Pasajero Anonimo", 0.0, List.of("soporte@aerolinea.com"));

        return Flux.just(r1, r2, r3, r4, r5)
                .filter(ReservationFilters.isValidReservation)
                .doOnNext(ReservationFilters.logReservation)
                .defaultIfEmpty(defaultReservation);
    }
}