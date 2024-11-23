package presentation.examples;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Callable;

public final class ExecutionLogger {

    private ExecutionLogger() {
    }

    public static void executeAndLogExecutionTime(Callable runnable) {
        long start = System.currentTimeMillis();
        Object result;
        try {
            result = runnable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println(
            String.format("%s Executed function with result: %s took %s", Thread.currentThread().getName(), result,
                Duration.of(end - start, ChronoUnit.MILLIS)));
    }
}
