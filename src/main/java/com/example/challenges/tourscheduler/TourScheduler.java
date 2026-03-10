package com.example.challenges.tourscheduler;

import java.time.LocalDateTime;
import java.util.*;

public class TourScheduler {
    public boolean canBookTour(List<Tour> existingTours, Tour request, int maxSimultaneous) {
        if (existingTours == null || request == null || !request.isValid()) {
            return false;
        }
        List<Tour> sortedExistingTours = existingTours
                .stream()
                .filter(Tour::isValid)
                .sorted(Comparator.comparing(Tour::startTime))
                .toList();

        PriorityQueue<Tour> currentTours = new PriorityQueue<>(Comparator.comparing(Tour::endTime));

        for (Tour existingTour : sortedExistingTours) {
            if (existingTour.startTime().isBefore(request.endTime()) && existingTour.endTime().isAfter(request.startTime())) {
                while (!currentTours.isEmpty() && !currentTours.peek().endTime().isAfter(existingTour.startTime())) {
                    currentTours.poll();
                }
                currentTours.offer(existingTour);
                if (currentTours.size() > maxSimultaneous - 1) {
                    return false;
                }
            }
        }

        return true;
    }
}

record Tour(String id, LocalDateTime startTime, LocalDateTime endTime) {
    public boolean isValid() {
        return id != null &&
                startTime != null &&
                endTime != null &&
                endTime.isAfter(startTime);
    }
}