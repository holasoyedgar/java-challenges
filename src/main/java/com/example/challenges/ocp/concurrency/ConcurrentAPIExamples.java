package com.example.challenges.ocp.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentAPIExamples {
    private static int counter = 0;
    private static AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Thread t1 = new Thread(() -> System.out.println("Hello"));
        Thread t2 = new Thread(() -> System.out.println("World"));
        Thread t3 = new Thread(() -> System.out.println("123"));
        service.execute(t1); // execute
        service.execute(t2);
        service.execute(t3);

        service.shutdown();

        ExecutorService service2 = Executors.newSingleThreadExecutor();
        Future<?> future = service2.submit(() -> { // submit
            for (int i = 0; i < 10_000_000; i++) {
                counter++;
            }
        });

        Future<?> future2 = service2.submit(() -> { // submit
            for (int i = 0; i < 10_000_000; i++) {
                counter++;
            }
        });

        try {
            var value = future.get(1, TimeUnit.MILLISECONDS);
            if (value == null) {
                System.out.println("The task was completed ");
            }
        } catch (TimeoutException e) {
            System.out.println("The task didn't complete on time");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            service2.shutdown();
        }

        ExecutorService service3 = Executors.newSingleThreadExecutor();

        try {
            Future<Integer> result = service3.submit(() -> 11 * 12);
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            service3.shutdown();
        }

        /*ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Runnable runnable = () -> System.out.println("hello");
        Callable<String> callable = () -> "Hi!";
        ScheduledFuture<?> scheduledFuture1 = scheduledExecutorService.schedule(runnable, 3, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture2 = scheduledExecutorService.schedule(callable, 1, TimeUnit.SECONDS);

        try {
            var string = scheduledFuture2.get();
            System.out.println(string);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        scheduledExecutorService.shutdown();*/

        ExecutorService service4 = Executors.newFixedThreadPool(2);
        service4.shutdown();
        ScheduledExecutorService service5 = Executors.newScheduledThreadPool(3);
        service5.shutdown();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                atomicCounter.incrementAndGet();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                atomicCounter.incrementAndGet();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(atomicCounter.get());
        System.out.println(counter);

        CyclicBarrier barrier = new CyclicBarrier(3);
        Runnable workerTask = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " is performing the first part of the work");
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }

            System.out.println(name + " is performing the remaining work after the barrier.");
        };

        Thread worker1 = new Thread(workerTask, "Worker 1");
        Thread worker2 = new Thread(workerTask, "Worker 2");
        Thread worker3 = new Thread(workerTask, "Worker 3");
        Thread worker4 = new Thread(workerTask, "Worker 4");
        worker1.start();
        worker2.start();
        worker3.start();
        worker4.start();
    }

    private static void someMethod() {
        counter++;
    }
}
