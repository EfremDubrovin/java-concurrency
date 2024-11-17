package falsesharing;

import java.time.Duration;

public class FalseSharingExample {

    public static void main(String[] args) throws InterruptedException {
        Counter c1 = new Counter();
        Counter c2 = c1;

        long iterationCount = 1_000_000_000L;

        long startTime = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            for (long i = 0; i < iterationCount; i++) {
                c1.counter1++;
            }
        });
        Thread thread2 = new Thread(() -> {
            for (long i = 0; i < iterationCount; i++) {
                c2.counter2++;
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        long endTime = System.currentTimeMillis();

        System.out.println("counter1 value: " + c1.counter1);
        System.out.println("counter2 value: " + c2.counter2);
        System.out.println("Elapsed duration: " + Duration.ofMillis(endTime - startTime));
    }
}
