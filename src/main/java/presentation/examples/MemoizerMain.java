package presentation.examples;

import static presentation.examples.ExecutionLogger.executeAndLogExecutionTime;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MemoizerMain {

    public static void main(String[] args) throws Exception {
        SumAllNumbers sumAllNumbers = new SumAllNumbers();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        Memoizer0<String, BigInteger> memoizer0 = new Memoizer0<>(sumAllNumbers);
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> executeAndLogExecutionTime(() -> memoizer0.apply("123456789")));
        }
        System.out.println("finished submission0");
        executor.awaitTermination(30, TimeUnit.SECONDS);

        System.out.println("start submission1");
        Memoizer1<String, BigInteger> memoizer1 = new Memoizer1<>(sumAllNumbers);
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> executeAndLogExecutionTime(() -> memoizer1.apply("123456789")));
        }
        System.out.println("finished submission1");
        executor.awaitTermination(30, TimeUnit.SECONDS);
        executor.shutdownNow();
    }


}
