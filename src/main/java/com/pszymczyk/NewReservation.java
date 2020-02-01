package com.pszymczyk;

import java.util.UUID;

public class NewReservation {

    public enum ReservationStatus {
        NEW,
        CONFIRMED,
        CANCELED;

    }
    private UUID id;

    private String userId;
    private ReservationStatus status;

    public CanceledReservation cancel() {
        if (status == ReservationStatus.CONFIRMED) {
            throw new IllegalStateException();
        }
        this.status = ReservationStatus.CANCELED;
        return new CanceledReservation(id, userId);
    }
    public ConfirmedReservation confirm() {
        if (status == ReservationStatus.CANCELED) {
            throw new IllegalStateException();
        }

        status = ReservationStatus.CONFIRMED;
        return new ConfirmedReservation(id, userId);
    }

    ReservationSummary newReservationSummary() {
        ReservationSummary reservationSummary = new ReservationSummary();
        reservationSummary.setStatus(ReservationStatus.NEW.toString());
        reservationSummary.setReservationId(getId().toString());
        return reservationSummary;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ReservationStatus getStatus() {
        return status;
    }

}
