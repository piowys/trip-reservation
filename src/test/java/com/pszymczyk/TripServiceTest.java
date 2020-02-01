package com.pszymczyk;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TripServiceTest {

    private TripService tripService;
    private TripRepository tripRepository;

    @BeforeEach
    void setup() {
        tripRepository = mock(TripRepository.class);
        tripService = new TripService(tripRepository);
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
        tripService.book(userId, tripCode);

        //then
        assertThat(trip.getReservations()).hasSize(1);
    }

    @Test
    void Should_book_trip_even_when_there_is_a_lot_not_confirmed_reservations() {
        //given
        String userId = "some-id";
        String tripCode = "some-trip";
        List<Reservation> reservations = new ArrayList<>();
        Reservation newReservation = new Reservation();
        newReservation.setUserId(userId);
        reservations.add(newReservation);
        Reservation canceledReservation = new Reservation();
        canceledReservation.setStatus(Reservation.ReservationStatus.CANCELED);
        reservations.add(canceledReservation);
        Trip trip = new Trip();
        trip.setTripCode(tripCode);
        trip.setReservations(reservations);
        trip.setSeats(1);
        when(tripRepository.findTrip(anyString())).thenReturn(trip);

        //when
        tripService.book(userId, tripCode);

        //then
        assertThat(trip.getReservations()).hasSize(3);
    }

    @Test
    void Should_throw_exception_when_cannot_find_trip() {
        //when
        Throwable thrown = catchThrowable(() -> tripService.book("some-id", "some-trip"));

        //then
        assertThat(thrown).isInstanceOf(TripNotFound.class);
    }
    
    @Test
    void Should_throw_exception_when_try_to_book_full_reserved_trip() {
        //given
        String userId = "some-id";
        String tripCode = "some-trip";
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setStatus(Reservation.ReservationStatus.CONFIRMED);
        reservations.add(reservation);
        Trip trip = new Trip();
        trip.setTripCode(tripCode);
        trip.setReservations(reservations);
        trip.setSeats(1);
        when(tripRepository.findTrip(anyString())).thenReturn(trip);

        //when
        Throwable thrown = catchThrowable(() -> tripService.book(userId, tripCode));

        //then
        assertThat(thrown).isInstanceOf(TripFullyBooked.class);
    }
}
