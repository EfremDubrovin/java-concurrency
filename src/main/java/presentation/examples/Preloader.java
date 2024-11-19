package presentation.examples;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Preloader {
    private final FutureTask<String> future = new FutureTask<>(() -> {
        Thread.sleep(1000);
        return "hey";
    });
    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public String getResult() {
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}