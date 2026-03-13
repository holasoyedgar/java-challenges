package com.example.challenges.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryManager {

    public ReservationResult processReservations(InventoryInput input) {
        List<SuccessfulReservation> successfulReservations = new ArrayList<>();
        List<FailedReservation> failedReservations = new ArrayList<>();
        Map<String, Integer> inventory = new HashMap<>(Map.copyOf(input.initialInventory()));
        List<ReservationRequest> requests = input.requests();

        for (ReservationRequest reservationRequest : requests) {
            int quantity = inventory.getOrDefault(reservationRequest.item(), 0);
            if (quantity >= reservationRequest.quantity()) {
                successfulReservations.add(new SuccessfulReservation(reservationRequest.item(), reservationRequest.quantity()));
                inventory.merge(reservationRequest.item(), -reservationRequest.quantity(), Integer::sum);
            } else {
                failedReservations.add(new FailedReservation(reservationRequest.item(), reservationRequest.quantity(), RejectionReason.OUT_OF_STOCK.toString()));
            }
        }
        return new ReservationResult(successfulReservations, failedReservations);
    }
}