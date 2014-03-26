/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.sum_sin;

/**
 *
 * @author lor1an
 */
public class PartSumThread extends Thread {

    private double resultOfThreadSum = 0;
    private int startArgumentBoundary, finishArgumentBoundary;

    private Function<Integer> f;
    public double threadSum;

    public PartSumThread(int startIndex, int endIndex, Function f) {
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
