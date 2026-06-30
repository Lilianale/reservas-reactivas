package com.aerolinea.reservas.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ReservationEvent {
    private final String id;
    private final String passengerName;
    private final Double price;
    private final List<String> emails;

    // Constructor con copia defensiva
    public ReservationEvent(String id, String passengerName, Double price, List<String> emails) {
        this.id = id;
        this.passengerName = passengerName;
        this.price = price;
        this.emails = emails != null ? new ArrayList<>(emails) : new ArrayList<>();
    }

    // Getters sin Setters (Inmutabilidad total)
    public String getId() { return id; }
    public String getPassengerName() { return passengerName; }
    public Double getPrice() { return price; }

    // Getter con copia defensiva no modificable
    public List<String> getEmails() {
        return Collections.unmodifiableList(this.emails);
    }

    @Override
    public String toString() {
        return "ReservationEvent{" +
                "id='" + id + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", price=" + price +
                ", emails=" + emails +
                '}';
    }
}