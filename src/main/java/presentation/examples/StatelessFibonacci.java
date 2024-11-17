package presentation.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Is this thread safe?
 */
public class StatelessFibonacci {

    public List<Long> fibonacci(long number) {
        long num1 = 0;
        long num2 = 1;
        List<Long> sequence = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            sequence.add(num1);
            long num3 = num2 + num1;
            num1 = num2;
            num2 = num3;
        }
        return sequence;
    }
}