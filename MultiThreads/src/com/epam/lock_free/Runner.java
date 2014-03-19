/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.lock_free;

import java.util.ArrayList;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class Runner {

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList();
        for (int i = 0; i < 5; i++) {
            threads.add(new BinarySequence().new InnerThread());
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
