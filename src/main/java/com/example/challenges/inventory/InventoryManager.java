package com.example.challenges.inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    public ReservationResult processReservations(InventoryInput input) {
        List<SuccessfulReservation> successfulReservations = new ArrayList<>();
        List<FailedReservation> failedReservations = new ArrayList<>();
        ActiveInventory activeInventory = new ActiveInventory(input.initialInventory());
        List<ReservationRequest> requests = input.requests();

        for (ReservationRequest reservationRequest : requests) {
            if (activeInventory.tryConsume(reservationRequest.item(), reservationRequest.quantity())) {
                successfulReservations.add(new SuccessfulReservation(reservationRequest.item(), reservationRequest.quantity()));
            } else {
                failedReservations.add(new FailedReservation(reservationRequest.item(), reservationRequest.quantity(), RejectionReason.OUT_OF_STOCK.toString()));
            }
        }
        return new ReservationResult(successfulReservations, failedReservations);
    }
}