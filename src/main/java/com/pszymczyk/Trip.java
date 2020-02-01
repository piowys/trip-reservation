package com.pszymczyk;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Trip {

    private String tripCode;
    private int seats;
    private List<NewReservation> newReservations = new ArrayList<>();
    private List<ConfirmedReservation> confirmedReservations = new ArrayList<>();

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

    public List<NewReservation> getNewReservations() {
        return newReservations;
    }

    public void setNewReservations(List<NewReservation> newReservations) {
        this.newReservations = newReservations;
    }

    void setConfirmedReservations(List<ConfirmedReservation> confirmedReservations) {
        this.confirmedReservations = confirmedReservations;
    }

    ReservationSummary requestReservation(String userId) {
        checkAvailability();
        NewReservation newReservation = newReservation(userId);
        getNewReservations().add(newReservation);
        return newReservation.newReservationSummary();
    }

    private void checkAvailability() {
            if (isFullyBooked()) {
            throw new TripFullyBooked(tripCode);
        }
    }

    private boolean isFullyBooked() {
        return confirmedReservations.size() >= getSeats();
    }

    private NewReservation newReservation(String userId) {
        NewReservation newReservation = new NewReservation();
        newReservation.setId(UUID.randomUUID());
        newReservation.setUserId(userId);
        return newReservation;
    }
}
