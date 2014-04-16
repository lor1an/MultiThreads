/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.circbuffer;

/**
 *
 * @author lor1an
 */
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static int SIZE_OF_BUFFER = 5;

    public static void main(String args[]) throws InterruptedException {
        CircularBuffer circularBuffer = new CircularBuffer(SIZE_OF_BUFFER);

        List<Thread> threadAdd = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            threadAdd.add(new Thread(new Producer(circularBuffer)));
            threadAdd.get(i).start();
        }

        List<Thread> threadGet = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            threadGet.add(new Thread(new Consumer(circularBuffer)));
            threadGet.get(i).start();
        }
    }
}
