package presentation.examples;

import java.util.function.Function;

public class Memoizer0<A, V> implements Function<A, V> {

    private final Function<A, V> function;

    public Memoizer0(Function<A, V> function) {
        this.function = function;
    }

    public synchronized V apply(A arg) {
        return function.apply(arg);
    }
}