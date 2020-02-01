package com.pszymczyk;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.catchThrowable;

class ReservationTest {

    @Test
    void Should_confirm_reservation() {
        //given
        Reservation reservation = new Reservation();

        //when
        reservation.confirm();

        //then
        Assertions.assertThat(reservation.isConfirmed()).isTrue();
    }

    @Test
    void Should_not_allow_to_confirm_canceled_reservation() {
        //given
        Reservation reservation = new Reservation();
        reservation.cancel();

        //when
        Throwable thrown = catchThrowable(reservation::confirm);

        //then
        Assertions.assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void Should_cancel_reservation() {
        //given
        Reservation reservation = new Reservation();

        //when
        reservation.cancel();

        //then
        Assertions.assertThat(reservation.isCanceled()).isTrue();
    }

    @Test
    void Should_not_allow_to_cancel_confirmed_reservation() {
        //given
        Reservation reservation = new Reservation();
        reservation.confirm();

        //when
        Throwable thrown = catchThrowable(reservation::cancel);

        //then
        Assertions.assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }

}
