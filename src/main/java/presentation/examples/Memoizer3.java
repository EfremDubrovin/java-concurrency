package presentation.examples;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.Function;

public class Memoizer3<A, V> implements Function<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Function<A, V> c;

    public Memoizer3(Function<A, V> c) {
        this.c = c;
    }

    public V apply(final A arg) {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = () -> c.apply(arg);
            FutureTask<V> ft = new FutureTask<>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run(); // call to c.apply happens here
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause()); // cause is the real exception during execution
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
