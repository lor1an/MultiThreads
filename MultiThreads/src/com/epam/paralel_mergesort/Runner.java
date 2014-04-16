/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.paralel_mergesort;

import static com.epam.paralel_mergesort.ParalelSort.createRandomArray;
import static com.epam.paralel_mergesort.ParalelSort.parallelMergeSort;

/**
 *
 * @author lor1an
 */
public class Runner {

    public final static int LENGTH = 100000;
    public final static int NUMBER_OF_THREADS = 4;

    public static void main(String[] args) {

        int[] a = createRandomArray(LENGTH);
        long startTime = System.currentTimeMillis();
        parallelMergeSort(a, NUMBER_OF_THREADS);
        System.out.println("Sort time: " + (System.currentTimeMillis() - startTime)
                + " ms");

//        for (int i = 0; i < a.length; i++) {
//            System.out.println("a[" + i + "]= " + a[i]);
//
//        }
    }
}
