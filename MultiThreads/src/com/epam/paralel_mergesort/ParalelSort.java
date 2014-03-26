/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.paralel_mergesort;

import java.util.Arrays;
import java.util.Random;

public class ParalelSort {

    public static void parallelMergeSort(int[] a, int numberOfThread) {
        pMergeSort(a, numberOfThread);
    }

    public static void pMergeSort(int[] a, int threadCount) {
        if (threadCount <= 1) {
            mergeSort(a);
        } else {
            int[] left = Arrays.copyOfRange(a, 0, a.length / 2);
            int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);

            SortInThread lThread = new SortInThread(left, threadCount - 2);
            lThread.start();
            mergeSort(right);
            try {
                lThread.join();
            } catch (InterruptedException ie) {
            }
            merge(left, right, a);
        }
    }

    public static void mergeSort(int[] a) {
        if (a.length >= 2) {
            int[] left = Arrays.copyOfRange(a, 0, a.length / 2);
            int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);
            mergeSort(left);
            mergeSort(right);
            merge(left, right, a);
        }
    }

    public static void merge(int[] left, int[] right, int[] a) {
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < a.length; i++) {
            if (i2 >= right.length || (i1 < left.length && left[i1] < right[i2])) {
                a[i] = left[i1];
                i1++;
            } else {
                a[i] = right[i2];
                i2++;
            }
        }
    }

    public static int[] createRandomArray(int length) {
        Random rand = new Random();
        int[] a = new int[length];
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(1000000);
        }
        return a;
    }

    static class SortInThread extends Thread {

        private int[] a;
        private int numberOfThread;

        public SortInThread(int[] a, int numberOfThread) {
            this.a = a;
            this.numberOfThread = numberOfThread;
        }

        @Override
        public void run() {
            pMergeSort(a, numberOfThread);
        }
    }
}
