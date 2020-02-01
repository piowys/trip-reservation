package com.pszymczyk;

class BookTripFeature {

    private final TripRepository tripRepository;

    BookTripFeature(TripRepository tripRepository, ReservationFactory reservationFactory) {
        this.tripRepository = tripRepository;
    }

    ReservationSummary book(String userId, String tripCode) {
        Trip trip = tripRepository.findTrip(tripCode);

        if (trip == null) {
            throw new TripNotFound(tripCode);
        }
        return trip.requestReservation(userId);
    }

}
