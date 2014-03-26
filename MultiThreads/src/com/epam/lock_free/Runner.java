/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.lock_free;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class Runner {

    public static void main(String[] args) {

        final AtomicBinarySequence sequence = new AtomicBinarySequence();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Thread.currentThread().getName()
                                    + ": " + sequence.next());
                            Thread.sleep(10);
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }, "Thread " + i).start();
        }

    }
}
