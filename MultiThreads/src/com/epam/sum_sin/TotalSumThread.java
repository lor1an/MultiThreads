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
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TotalSumThread {

    static int threadsNumberYouWant = 2;
    static int N = 10;
    volatile static double totalSum = 0;

    public static void main(String[] args) {
        System.out.println("Sum has " + (2 * N + 1) + " terms!");
        if (threadsNumberYouWant > 2 * N + 1) {
            threadsNumberYouWant = 2 * N + 1;
        }
        int parts = (2 * N + 1) / threadsNumberYouWant;
        int startValue = -N;
        boolean flag = true;

        if (flag) {
            calclulateWithThreads(startValue, parts);
        } else {
            calclulateWithCallable(startValue, parts);
        }

        System.out.println("TotalSum = " + totalSum);

    }

    public static void calclulateWithThreads(int startValue, int parts) {
        ArrayList<ThreadSum> threads = new ArrayList<>();
        int endValue;
        for (int i = 0; i < threadsNumberYouWant; i++) {
            if (i < threadsNumberYouWant - 1) {
                endValue = startValue + parts;
            } else {
                endValue = N + 1;
            }
            threads.add(
                    new ThreadSum(startValue, endValue - 1, new Function<Integer>() {
                        @Override
                        public Double function(Integer argument) {
                            return Math.sin(argument);
                        }
                    })
            );
            threads.get(i).start();
            startValue = endValue;
        }
        for (int j = 0; j < threads.size(); j++) {
            try {
                threads.get(j).join();
                totalSum += threads.get(j).threadSum;
            } catch (InterruptedException ex) {
                Logger.getLogger(TotalSumThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void calclulateWithCallable(int startValue, int parts) {
        ExecutorService es1 = Executors.newFixedThreadPool(threadsNumberYouWant);
        Collection<Future<Double>> c = new ArrayList<>();
        int endValue;
        for (int i = 0; i < threadsNumberYouWant; i++) {
            if (i < threadsNumberYouWant - 1) {
                endValue = startValue + parts;
            } else {
                endValue = N + 1;
            }
            c.add(es1.submit(new CallableSum(startValue, endValue - 1, new Function<Integer>() {
                @Override
                public Double function(Integer argument) {
                    return Math.sin(argument);
                }
            })));
            startValue = endValue;
        }
        es1.shutdown();
        for (Future<Double> future : c) {
            try {
                totalSum += future.get();
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(TotalSumThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
