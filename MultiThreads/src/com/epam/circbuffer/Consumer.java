/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.circbuffer;

/**
 *
 * @author lor1an
 */
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable {

    private CircularBuffer circularBuffer;

    public Consumer(CircularBuffer circularBuffer) {
        this.circularBuffer = circularBuffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                circularBuffer.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
