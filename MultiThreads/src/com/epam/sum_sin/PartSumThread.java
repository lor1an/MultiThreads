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
    private TotalSumThread mainThread;

    public PartSumThread(int startIndex, int endIndex, TotalSumThread mainThread) {
        startArgumentBoundary = startIndex;
        finishArgumentBoundary = endIndex;
        this.mainThread = mainThread;
    }

    @Override
    public void run() {
        resultOfThreadSum = thisThreadSum();
        mainThread.computeTotalSum(resultOfThreadSum);
    }

    public double thisThreadSum() {
        double threadSum = 0;
        int argument = startArgumentBoundary;
        while (argument <= finishArgumentBoundary) {
            threadSum += Math.sin(argument);
            argument += 1;
        }
        return threadSum;
    }
}
