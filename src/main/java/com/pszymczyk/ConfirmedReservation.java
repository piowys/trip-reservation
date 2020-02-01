package com.pszymczyk;

import java.util.UUID;

class ConfirmedReservation {

    private final UUID id;
    private final String userId;

    public ConfirmedReservation(UUID id, String userId) {
        this.id = id;
        this.userId = userId;
    }
}
