package com.example.challenges.viewtracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ViewTrackerTest {

    @Test
    void testConcurrentViews_HappyPath() throws InterruptedException {
        ViewTracker tracker = new ViewTracker();
        String id = "Z1";
        int totalThreads = 1000;

        ExecutorService executor = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(totalThreads);

        for (int i = 0; i < totalThreads; i++) {
            executor.submit(() -> {
                tracker.recordView(id);
                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();

        Assertions.assertEquals(1000, tracker.getViews(id), "La cuenta final debe ser exacta tras ejecución concurrente");
    }

    @Test
    void testMultipleKeysConcurrency_Isolation() throws InterruptedException {
        ViewTracker tracker = new ViewTracker();
        int totalProperties = 50;
        int viewsPerProperty = 1000;

        ExecutorService executor = Executors.newFixedThreadPool(200);
        CountDownLatch latch = new CountDownLatch(totalProperties * viewsPerProperty);

        for (int i = 0; i < totalProperties; i++) {
            String id = "PROP_" + i;
            for (int j = 0; j < viewsPerProperty; j++) {
                executor.submit(() -> {
                    tracker.recordView(id);
                    latch.countDown();
                });
            }
        }

        latch.await();
        executor.shutdown();

        for (int i = 0; i < totalProperties; i++) {
            Assertions.assertEquals(1000, tracker.getViews("PROP_" + i),
                    "Fallo de aislamiento: La propiedad " + i + " perdió la cuenta.");
        }
    }

    @Test
    @Timeout(value = 1500, unit = TimeUnit.MILLISECONDS)
    void testHotKeyPerformance_RealStressTest() throws InterruptedException {
        ViewTracker tracker = new ViewTracker();
        String viralListingId = "VIRAL_HOUSE_999";

        int totalThreads = 500;
        int viewsPerThread = 100_000;

        ExecutorService executor = Executors.newFixedThreadPool(totalThreads);
        CountDownLatch latch = new CountDownLatch(totalThreads);

        for (int i = 0; i < totalThreads; i++) {
            executor.submit(() -> {
                for (int j = 0; j < viewsPerThread; j++) {
                    tracker.recordView(viralListingId);
                }
                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();

        Assertions.assertEquals(50_000_000, tracker.getViews(viralListingId));
    }
}