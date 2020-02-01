package com.pszymczyk;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TripServiceTest {

    private BookTripFeature bookTripFeature;
    private TripRepository tripRepository;

    @BeforeEach
    void setup() {
        tripRepository = mock(TripRepository.class);
        bookTripFeature = new BookTripFeature(tripRepository, reservationFactory);
    }

    @Test
    void Should_book_trip() {
        //given
        String userId = "some-id";
        String tripCode = "some-trip";
        Trip trip = new Trip();
        trip.setTripCode(tripCode);
        trip.setSeats(1);
        when(tripRepository.findTrip(anyString())).thenReturn(trip);

        //when
        bookTripFeature.book(userId, tripCode);

        //then
        assertThat(trip.getNewReservations()).hasSize(1);
    }

    @Test
    void Should_book_trip_even_when_there_is_a_lot_not_confirmed_reservations() {
        //given
        String userId = "some-id";
        String tripCode = "some-trip";
        List<NewReservation> newReservations = new ArrayList<>();
        NewReservation newReservation = new NewReservation();
        newReservation.setUserId(userId);
        newReservations.add(newReservation);
        newReservations.add(newReservation);
        Trip trip = new Trip();
        trip.setTripCode(tripCode);
        trip.setNewReservations(newReservations);
        trip.setSeats(1);
        when(tripRepository.findTrip(anyString())).thenReturn(trip);

        //when
        bookTripFeature.book(userId, tripCode);

        //then
        assertThat(trip.getNewReservations()).hasSize(3);
    }

    @Test
    void Should_throw_exception_when_cannot_find_trip() {
        //when
        Throwable thrown = catchThrowable(() -> bookTripFeature.book("some-id", "some-trip"));

        //then
        assertThat(thrown).isInstanceOf(TripNotFound.class);
    }
    
    @Test
    void Should_throw_exception_when_try_to_book_full_reserved_trip() {
        //given
        String userId = "some-id";
        String tripCode = "some-trip";
        ConfirmedReservation confirmedReservation = new ConfirmedReservation(UUID.randomUUID(), userId);
        List<ConfirmedReservation> confirmedReservations = new ArrayList<>();
        confirmedReservations.add(confirmedReservation);
        Trip trip = new Trip();
        trip.setTripCode(tripCode);
        trip.setConfirmedReservations(confirmedReservations);
        trip.setSeats(1);
        when(tripRepository.findTrip(anyString())).thenReturn(trip);

        //when
        Throwable thrown = catchThrowable(() -> bookTripFeature.book(userId, tripCode));

        //then
        assertThat(thrown).isInstanceOf(TripFullyBooked.class);
    }
}
