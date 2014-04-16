package com.epam.sum_sin;

/**
 *
 * @author Anatolii_Hlazkov
 */
import java.util.concurrent.Callable;

public class CallableSum implements Callable<Double> {

    private final int startArgumentBoundary, finishArgumentBoundary;
    private final Function<Integer> f;

    public CallableSum(int startIndex, int endIndex, Function f) {
        startArgumentBoundary = startIndex;
        finishArgumentBoundary = endIndex;
        this.f = f;
    }

    @Override
    public Double call() throws Exception {
        double threadSum = 0;
        int argument = startArgumentBoundary;
        while (argument <= finishArgumentBoundary) {
            threadSum += f.function(argument);
            argument += 1;
        }
        return threadSum;
    }

}
