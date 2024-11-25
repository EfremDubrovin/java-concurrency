package presentation.examples;

import java.util.concurrent.CountDownLatch;

public class TestHarness {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {

        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try {
                    log("waiting players to start.");
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                        log("player has finished");
                    }
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            });
            t.start();
        }
        long start = System.nanoTime();
        Thread.sleep(1000);
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        TestHarness testHarness = new TestHarness();
        testHarness.timeTasks(3, () -> log("task is executing"));
    }

    private static void log(String message) {
        System.out.println(Thread.currentThread().getName() + " " + message);
    }
}