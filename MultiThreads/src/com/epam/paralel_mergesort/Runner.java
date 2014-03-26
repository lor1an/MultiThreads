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

    public static void main(String[] args) {
        int LENGTH = 1000;

        int[] a = createRandomArray(LENGTH);
        parallelMergeSort(a, 4);
        for (int i = 0; i < a.length; i++) {
            System.out.println("a[" + i + "]= " + a[i]);

        }

    }
}
