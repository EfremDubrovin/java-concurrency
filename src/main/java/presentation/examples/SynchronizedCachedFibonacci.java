package presentation.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SynchronizedCachedFibonacci {
    private final AtomicReference<Long> lastNumber = new AtomicReference<>();
    private final AtomicReference<List<Long>> lastResult = new AtomicReference<>();
    public synchronized List<Long> fibonacci(long number) {
        if (number == lastNumber.get()) return lastResult.get();
        long num1 = 0;
        long num2 = 1;
        List<Long> sequence = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            sequence.add(num1);
            long num3 = num2 + num1;
            num1 = num2;
            num2 = num3;
        }
        lastNumber.set(num1);
        lastResult.set(sequence);
        return sequence;
    }
}
