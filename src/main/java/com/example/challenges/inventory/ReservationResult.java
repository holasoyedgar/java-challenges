package com.example.challenges.inventory;

import java.util.List;

public record ReservationResult(
        List<SuccessfulReservation> successful,
        List<FailedReservation> failed
) {}
