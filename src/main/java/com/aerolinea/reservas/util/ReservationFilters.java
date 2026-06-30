package com.aerolinea.reservas.util;

import com.aerolinea.reservas.model.ReservationEvent;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ReservationFilters {

    // Predicado que valida precio > 0 y que la lista de emails no venga vacía
    public static final Predicate<ReservationEvent> isValidReservation = event ->
            event.getPrice() != null && event.getPrice() > 0 &&
                    event.getEmails() != null && !event.getEmails().isEmpty();

    // Consumidor que imprime el evento procesado en la consola del servidor
    public static final Consumer<ReservationEvent> logReservation = event ->
            System.out.println("[PROCESANDO EVENTO] -> " + event);
}