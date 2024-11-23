package presentation.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Memoizer1<A, V> implements Function<A, V> {

    private final Map<A, V> cache = new HashMap<>();
    private final Function<A, V> function;

    public Memoizer1(Function<A, V> function) {
        this.function = function;
    }

    public synchronized V apply(A arg) {
        V result = cache.get(arg);
        if (result == null) {
            result = function.apply(arg);
            cache.put(arg, result);
        }
        return result;
    }
}