package com.pszymczyk;

import java.util.UUID;

public class Reservation {

    public enum ReservationStatus {
        NEW,
        CONFIRMED,
        CANCELED;

    }
    private UUID id;

    private String userId;
    private ReservationStatus status;
    ReservationSummary newReservationSummary() {
        ReservationSummary reservationSummary = new ReservationSummary();
        reservationSummary.setStatus(ReservationStatus.NEW.toString());
        reservationSummary.setReservationId(getId().toString());
        return reservationSummary;
    }
    public void cancel() {
        if (status == ReservationStatus.CONFIRMED) {
            throw new IllegalStateException();
        }
        this.status = ReservationStatus.CANCELED;
    }

    public boolean isCanceled() {
        return status == ReservationStatus.CANCELED;
    }

    public void confirm() {
        if (status == ReservationStatus.CANCELED) {
            throw new IllegalStateException();
        }

        status = ReservationStatus.CONFIRMED;
    }

    boolean isConfirmed() {
        return getStatus() == ReservationStatus.CONFIRMED;
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

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
