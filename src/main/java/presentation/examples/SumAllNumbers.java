package presentation.examples;

import java.math.BigInteger;
import java.util.function.Function;

public class SumAllNumbers implements Function<String, BigInteger> {

    @Override
    public BigInteger apply(String s) {
        long parsedLong = Long.parseLong(s);
        BigInteger sum = BigInteger.ZERO;
        for (long i = 0; i < parsedLong; i++) {
            sum = sum.add(BigInteger.valueOf(i));
        }
        return sum;
    }
}
