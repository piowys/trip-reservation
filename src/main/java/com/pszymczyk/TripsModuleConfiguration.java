package com.pszymczyk;

class TripsModuleConfiguration {

    TripsFacade tripsFacade() {
        return tripsFacade(new InMemoryTripRepository());
    }

    TripsFacade tripsFacade(TripRepository tripRepository) {
        ReservationFactory reservationFactory = new ReservationFactory();
        BookTripFeature bookTripFeature = new BookTripFeature(tripRepository, reservationFactory);
        ConfirmReservationFeature confirmReservationFeature = new ConfirmReservationFeature();
        return new TripsFacade(bookTripFeature, confirmReservationFeature);
    }
}
