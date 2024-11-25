package presentation.examples;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class Memoizer5<A, V> implements Function<A, V> {
    private final ConcurrentMap<A, V> cache = new ConcurrentHashMap<>();
    private final ConcurrentMap<A, List<String>> descriptionCache = new ConcurrentHashMap<>();

    private final Function<A, V> c;

    public Memoizer5(Function<A, V> c) {
        this.c = c;
    }

    public V apply(final A arg) {
        return cache.computeIfAbsent(arg, c);
    }

    public void updateValue(final A arg, final V res, List<String> newDescriptions) {
        cache.compute(arg, (rOld, rNew) -> {
            descriptionCache.putIfAbsent(arg, newDescriptions);
            return res;
        });
    }
}