package presentation.examples;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Memoizer2<A, V> implements Function<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Function<A, V> c;

    public Memoizer2(Function<A, V> c) {
        this.c = c;
    }

    public V apply(A arg) {
        V result = cache.get(arg);
        if (result == null) {
            result = c.apply(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
