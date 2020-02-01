package com.pszymczyk;

import java.util.UUID;

class CanceledReservation {

    private final UUID id;
    private final String userId;

    public CanceledReservation(UUID id, String userId) {
        this.id = id;
        this.userId = userId;
    }
}
