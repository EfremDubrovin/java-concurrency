package presentation.examples;

import java.util.List;

public class FibonacciMain {

    public static void main(String[] args) {
        printFibonacci(10L);
        printFibonacci(100L);
        printFibonacci(1_000L);
        printFibonacci(100_000L);
        printFibonacci(1_000_000L);
        printFibonacci(10_000_000L);
    }

    private static void printFibonacci(long number) {
        StatelessFibonacci generator = new StatelessFibonacci();
        long start = System.currentTimeMillis();
        List<Long> sequence = generator.fibonacci(number);
        long end = System.currentTimeMillis();
        System.out.printf("Fibonacci of %s elements, took %sms%n", sequence.size(), end - start);
    }
}
