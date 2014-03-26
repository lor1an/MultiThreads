package com.epam.sum_sin;

/**
 *
 * @author Anatolii_Hlazkov
 */
import java.io.IOException;
import java.util.concurrent.Callable;

public class ExecutableSin implements Callable<String> {

    @Override
    public String call() {
        System.out.println("task is processing");
        return "result ";
    }
}
