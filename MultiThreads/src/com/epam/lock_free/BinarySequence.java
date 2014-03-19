/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.lock_free;

import java.math.BigInteger;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class BinarySequence {

    private static final BigInteger TWO = new BigInteger("2");
    private static BigInteger current = new BigInteger("1");

    public static BigInteger next() {
        BigInteger currentRet = current;
        synchronized (TWO) {
            System.out.println(current);
            current = current.multiply(TWO);
        }
        return currentRet;
    }

    public class InnerThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                next();
            }
        }
    }

}
