/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.sum_sin;

/**
 *
 * @author lor1an
 */
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TotalSumThread {
    /*
     * arguments in the sum : [-N;N]
     * max argument in the sum -  maxArgumentOfSum [in the task - N]
     */

    static int threadsNumberYouWant = 20;
    static int maxArgumentOfSum = 100;
    volatile double totalSum = 0;

    public static void main(String[] args) {
        System.out.println("Sum has " + (2 * maxArgumentOfSum + 1) + " terms!");
        System.out.println("Number of threads you want : " + threadsNumberYouWant);
        new TotalSumThread().run(maxArgumentOfSum);
    }

    public void computeTotalSum(double result) {
        totalSum += result;
    }

    private void run(int N) {

        if (threadsNumberYouWant > 2 * N + 1) {
            System.out.println("Number of threads you want bigger than number of terms in the sum!");
            threadsNumberYouWant = 2 * N + 1;
            System.out.println("Number of threads was changed  : " + threadsNumberYouWant);
        }

        int parts = (2 * N + 1) / threadsNumberYouWant;
        int endValue, startValue = -N;

        ArrayList<PartSumThread> thread = new ArrayList<>();
        for (int i = 0; i < threadsNumberYouWant; i++) {
            if (i < threadsNumberYouWant - 1) {
                endValue = startValue + parts;
            } else {
                endValue = N + 1;
            }
            thread.add(
                    new PartSumThread(startValue, endValue - 1, new Function<Integer>() {
                        public Double function(Integer argument) {
                            return Math.sin(argument);
                        }
                    })
            );
            thread.get(i).start();
            startValue = endValue;
        }

        for (int j = 0; j < thread.size(); j++) {
            try {
                thread.get(j).join();
                totalSum += thread.get(j).threadSum;
            } catch (InterruptedException ex) {
                Logger.getLogger(TotalSumThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Number of threads you have : " + thread.size());
        System.out.println("TotalSum = " + totalSum);
    }
}
