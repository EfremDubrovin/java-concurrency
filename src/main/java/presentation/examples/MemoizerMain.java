package presentation.examples;

import static presentation.examples.ExecutionLogger.executeAndLogExecutionTime;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MemoizerMain {

    public static void main(String[] args) throws Exception {
        SumAllNumbers sumAllNumbers = new SumAllNumbers();

        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            List<Runnable> runnables = executor.shutdownNow();
        }));
//        Memoizer0<String, BigInteger> memoizer0 = new Memoizer0<>(sumAllNumbers);
//        for (int i = 0; i < threadCount; i++) {
//            executor.execute(() -> executeAndLogExecutionTime(() -> memoizer0.apply("123456789")));
//        }
//        System.out.println("finished submission0");
//        executor.awaitTermination(20, TimeUnit.SECONDS);

//        System.out.println("start submission1");
//        Memoizer1<String, BigInteger> memoizer1 = new Memoizer1<>(sumAllNumbers);
//        for (int i = 0; i < threadCount; i++) {
//            executor.execute(() -> executeAndLogExecutionTime(() -> memoizer1.apply("123456789")));
//        }
//        System.out.println("finished submission1");
//        TimeUnit.SECONDS.sleep(10);

//        System.out.println("start submission2");
//        Memoizer2<String, BigInteger> memoizer2 = new Memoizer2<>(sumAllNumbers);
//        for (int i = 0; i < threadCount; i++) {
//            executor.execute(() -> executeAndLogExecutionTime(() -> memoizer2.apply("123456789")));
//        }
//        System.out.println("finished submission2");
//        TimeUnit.SECONDS.sleep(10);

//        System.out.println("start submission3");
//        Memoizer3<String, BigInteger> memoizer3 = new Memoizer3<>(sumAllNumbers);
//        for (int i = 0; i < threadCount; i++) {
//            executor.execute(() -> executeAndLogExecutionTime(() -> memoizer3.apply("123456789")));
//        }
//        System.out.println("finished submission3");
//        TimeUnit.SECONDS.sleep(10);

//        System.out.println("start submission4");
//        Memoizer4<String, BigInteger> memoizer4 = new Memoizer4<>(sumAllNumbers);
//        for (int i = 0; i < threadCount; i++) {
//            executor.execute(() -> executeAndLogExecutionTime(() -> memoizer4.apply("123456789")));
//        }
//        System.out.println("finished submission4");
//        TimeUnit.SECONDS.sleep(10);

        System.out.println("start submission5");
        Memoizer5<String, BigInteger> memoizer5 = new Memoizer5<>(sumAllNumbers);
        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> executeAndLogExecutionTime(() -> memoizer5.apply("123456789")));
        }
        System.out.println("finished submission5");
        TimeUnit.SECONDS.sleep(10);

        executor.shutdownNow();
    }


}
