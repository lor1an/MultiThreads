/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.circbuffer;

/**
 *
 * @author lor1an
 */
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {

    int elementToAdd;
    private CircularBuffer circularBuffer;
    private final Random rand = new Random();

    public Producer(CircularBuffer circularBuffer) {
        this.circularBuffer = circularBuffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                circularBuffer.set(elementToAdd);
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
            elementToAdd = rand.nextInt(1000);
        }
    }
}
