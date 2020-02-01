package com.pszymczyk;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Trip {

    private String tripCode;
    private int seats;
    private List<Reservation> reservations = new ArrayList<>();

    public String getTripCode() {
        return tripCode;
    }

    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    ReservationSummary requestReservation(String userId) {
        checkAvailability();
        Reservation reservation = newReservation(userId);
        getReservations().add(reservation);
        return reservation.newReservationSummary();
    }

    private void checkAvailability() {
        if (isFullyBooked()) {
            throw new TripFullyBooked(tripCode);
        }
    }

    private boolean isFullyBooked() {
        return getReservationsCount() >= getSeats();
    }

    private long getReservationsCount() {
        return getReservations().stream().filter(Reservation::isConfirmed).count();
    }

    private Reservation newReservation(String userId) {
        Reservation reservation = new Reservation();
        reservation.setId(UUID.randomUUID());
        reservation.setStatus(Reservation.ReservationStatus.NEW);
        reservation.setUserId(userId);
        return reservation;
    }
}
