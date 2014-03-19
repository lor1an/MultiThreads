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

public class Producer implements Runnable {

    volatile int elementToAdd;
    private CircularBuffer circularBuffer;

    public Producer(CircularBuffer circularBuffer) {
        this.circularBuffer = circularBuffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Write" + Thread.currentThread().getName());
               
                circularBuffer.addElementIntoBuffer(elementToAdd);
                System.out.println("Success write : " + elementToAdd);
                System.out.println(circularBuffer.toString());
                elementToAdd++;
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}