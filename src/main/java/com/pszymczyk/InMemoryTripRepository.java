package com.pszymczyk;

import java.util.HashMap;
import java.util.Map;

class InMemoryTripRepository implements TripRepository {

    private final Map<String, Trip> db = new HashMap<>();

    @Override
    public Trip findTrip(String tripCode) {
        return db.get(tripCode);
    }

    @Override
    public void save(Trip trip) {
        db.put(trip.getTripCode(), trip);
    }
}
