/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.sum_sin;

/**
 *
 * @author lor1an
 */
public class ThreadSum extends Thread {

    private double resultOfThreadSum = 0;
    private int startArgumentBoundary, finishArgumentBoundary;

    private final Function<Integer> f;
    public double threadSum;

    public ThreadSum(int startIndex, int endIndex, Function f) {
        startArgumentBoundary = startIndex;
        finishArgumentBoundary = endIndex;
        this.f = f;
    }

    @Override
    public void run() {
        Integer argument = startArgumentBoundary;
        while (argument <= finishArgumentBoundary) {
            threadSum += f.function(argument);
            argument += 1;
        }
    }

}
