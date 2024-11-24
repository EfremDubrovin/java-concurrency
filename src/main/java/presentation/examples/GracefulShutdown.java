package presentation.examples;

import java.util.List;
import java.util.concurrent.Executors;

public class GracefulShutdown {

    private LogService logService = new LogService();

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> logService.stop()));
    }

    private class LogService {

        private final TrackingExecutor executorService = new TrackingExecutor(Executors.newSingleThreadExecutor());

        public void stop() {
            List<Runnable> runnables = executorService.shutdownNow();
            // do something with not started tasks...
            List<Runnable> cancelledTasks = executorService.getCancelledTasks();
            // do something with cancelled tasks...
        }
    }
}
