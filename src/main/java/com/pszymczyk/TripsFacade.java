package com.pszymczyk;

import java.util.UUID;

class TripsFacade {

    private final BookTripFeature bookTripFeature;
    private final ConfirmReservationFeature confirmReservationFeature;

    TripsFacade(BookTripFeature bookTripFeature, ConfirmReservationFeature confirmReservationFeature) {
        this.bookTripFeature = bookTripFeature;
        this.confirmReservationFeature = confirmReservationFeature;
    }

    ReservationSummary book(String userId, String tripCode) {
        return bookTripFeature.book(userId, tripCode);
    }

    void confirm(UUID reservationId) {
        confirmReservationFeature.confirm(reservationId);
    }
}
