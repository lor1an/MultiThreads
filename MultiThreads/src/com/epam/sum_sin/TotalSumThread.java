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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TotalSumThread {

    static int threadsNumberYouWant = 8;
    static int maxArgumentOfSum = 100;
    volatile double totalSum = 0;

    public static void main(String[] args) {
        System.out.println("Sum has " + (2 * maxArgumentOfSum + 1) + " terms!");
        System.out.println("Number of threads you want : " + threadsNumberYouWant);
        new TotalSumThread().run(maxArgumentOfSum);
    }
    Future<Double> collection;

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

//        ArrayList<PartSumThread> thread = new ArrayList<>();
//        for (int i = 0; i < threadsNumberYouWant; i++) {
//            if (i < threadsNumberYouWant - 1) {
//                endValue = startValue + parts;
//            } else {
//                endValue = N + 1;
//            }
//            thread.add(
//                    new PartSumThread(startValue, endValue - 1, new Function<Integer>() {
//                        public Double function(Integer argument) {
//                            return Math.sin(argument);
//                        }
//                    })
//            );
//            thread.get(i).start();
//            startValue = endValue;
//        }
//
//        for (int i = 0; i < threadsNumberYouWant; i++) {
//            if (i < threadsNumberYouWant - 1) {
//                endValue = startValue + parts;
//            } else {
//                endValue = N + 1;
//            }
//            thread.add(
//                    new PartSumThread(startValue, endValue - 1, new Function<Integer>() {
//                        public Double function(Integer argument) {
//                            return Math.sin(argument);
//                        }
//                    })
//            );
//            thread.get(i).start();
//            startValue = endValue;
//        }
        ExecutorService es1 = Executors.newFixedThreadPool(threadsNumberYouWant);
        ArrayList<Future<Double>> thread1 = new ArrayList<>();
        for (int i = 0; i < threadsNumberYouWant; i++) {
            if (i < threadsNumberYouWant - 1) {
                endValue = startValue + parts;
            } else {
                endValue = N + 1;
            }

            //поместить задачу в очередь на выполнение
            thread1.add(es1.submit(new CallableSum(startValue, endValue - 1, new Function<Integer>() {
                public Double function(Integer argument) {
                    return Math.sin(argument);
                }
            })));
        }

        for (Future<Double> future : thread1) {
            while (future.isDone()) {
                try {
                    totalSum += future.get();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        es1.shutdown();
//        for (int j = 0; j < thread.size(); j++) {
//            try {
//                thread.get(j).join();
//                totalSum += thread.get(j).threadSum;
//            } catch (InterruptedException ex) {
//                Logger.getLogger(TotalSumThread.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        System.out.println("Number of threads you have : " + thread1.size());
        System.out.println("TotalSum = " + totalSum);
    }
}
