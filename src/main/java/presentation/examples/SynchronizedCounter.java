package presentation.examples;

public class SynchronizedCounter {
    protected int counter;
    public synchronized void increment() {
        counter++;
    }
}
class SynchronizedLoggingCounter extends SynchronizedCounter {
    public synchronized void incrementAndLog() {
        super.increment();
        System.out.printf("Counter value: %s", counter);
    }
}
