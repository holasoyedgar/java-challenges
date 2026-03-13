package com.example.challenges.inventory;

import java.util.List;
import java.util.Map;

public record InventoryInput(
        Map<String, Integer> initialInventory,
        List<ReservationRequest> requests
) {}
