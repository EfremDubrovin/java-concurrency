package presentation.examples;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDeadlock {

    public static void main(String[] args) {

        class HtmlRenderer implements Runnable {
            private final ExecutorService exec = Executors.newSingleThreadExecutor();
            public void run() {
                exec.submit(ThreadDeadlock::blockingSleeperTask);
                exec.submit(ThreadDeadlock::blockingSleeperTask);
            }
        }

        new HtmlRenderer().run();
    }

    private static void blockingSleeperTask() {
        try {
            System.out.println(Thread.currentThread().getName() + " starting job");
            Thread.sleep(100_000_000);
            System.out.println(Thread.currentThread().getName() + " finishing job");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}