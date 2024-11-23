package presentation.examples;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceExample {

    public static void main(String[] args) {
        ExecutorService completionServiceExecutor = Executors.newFixedThreadPool(3);
        CompletionService<String> completionService = new ExecutorCompletionService(completionServiceExecutor);

        for (int i = 0; i < 10; i++) {
            completionService.submit(() -> "hey");
        }

        for (int i = 0; i < 10; i++) {
            try {
                Future<String> result = completionService.take(); // blocking
                System.out.println(result.get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        completionServiceExecutor.shutdown();
    }
}
