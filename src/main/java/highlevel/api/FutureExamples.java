package highlevel.api;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FutureExamples {


    public static void main(String[] args) throws InterruptedException {
        cancelRunningTasksExample();

    }

    /**
     * Here we can experiment with scheduling tasks
     */
    private static void cancelRunningTasksExample() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        Future<?> handledRunner = executorService.schedule(new ProperlyHandledInterrupt(), 0, TimeUnit.SECONDS);
        Future<?> unhandledRunner = executorService.schedule(new UnproperlyHandledInterrupt(), 0, TimeUnit.SECONDS);

        Thread.sleep(10); // no guarantees that the tasks will start before getting cancelled!

        boolean handledRunnerGotCancelled = handledRunner.cancel(true); // cancel
        System.out.println("handledRunnerGotCancelled " + handledRunnerGotCancelled);
        boolean unhandledRunnerGotCancelled = unhandledRunner.cancel(true); // cancel
        System.out.println("unhandledRunnerGotCancelled " + unhandledRunnerGotCancelled);
        executorService.shutdown();
    }

    private static class ProperlyHandledInterrupt implements Runnable {
        @Override
        public void run() {
            int counter = 1;
            while (true) {
                try {
                    String currentThreadName = Thread.currentThread().getName();
                    System.out.println(currentThreadName + " in loop of handled: " + counter);
                    counter++;
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("got interrupted");
                    return;
                }
            }
        }
    }

    private static class UnproperlyHandledInterrupt implements Runnable {
        @Override
        public void run() {
            int counter = 1;
            while (true) {
                try {
                    String currentThreadName = Thread.currentThread().getName();
                    System.out.println(currentThreadName + " in loop of unhandled: " + counter);
                    counter++;
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("interrupt caught and ignored");
                }
            }
        }
    }
}
