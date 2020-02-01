package com.pszymczyk;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ReservationTest {

    @Test
    void Should_confirm_reservation() {
        //given
        NewReservation newReservation = new NewReservation();

        //when
        ConfirmedReservation confirmedReservation = newReservation.confirm();

        //then
        assertThat(confirmedReservation).isNotNull();
    }

    @Test
    void Should_not_allow_to_confirm_canceled_reservation() {
        //given
        NewReservation newReservation = new NewReservation();
        newReservation.cancel();

        //when
        Throwable thrown = catchThrowable(newReservation::confirm);

        //then
        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void Should_cancel_reservation() {
        //given
        NewReservation newReservation = new NewReservation();

        //when
        CanceledReservation canceledReservation = newReservation.cancel();

        //then
        assertThat(canceledReservation).isNotNull();
    }

    @Test
    void Should_not_allow_to_cancel_confirmed_reservation() {
        //given
        NewReservation newReservation = new NewReservation();
        newReservation.confirm();

        //when
        Throwable thrown = catchThrowable(newReservation::cancel);

        //then
        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }

}
