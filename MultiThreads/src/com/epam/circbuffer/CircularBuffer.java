/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.circbuffer;

import java.util.Arrays;

/**
 *
 * @author lor1an
 */
public class CircularBuffer {

    volatile Integer elementsOfBuffer[];
    volatile static int head;
    volatile static int tail;

    public CircularBuffer(Integer sizeOfBuffer) {
        elementsOfBuffer = new Integer[sizeOfBuffer];
        head = 0;
        tail = 0;
    }

    public synchronized boolean addElementIntoBuffer(Integer elementToAdd) throws InterruptedException {
        while (elementsOfBuffer[head] != null && tail == 0) {
            wait();
        }
        elementsOfBuffer[tail++] = elementToAdd;
        if (tail == elementsOfBuffer.length) {
            tail = 0;
        }
        notifyAll();
        return true;
    }

    public synchronized Integer get() throws InterruptedException {
        while (elementsOfBuffer[head] == null) {
            wait();
        }
        int value = elementsOfBuffer[head];
        elementsOfBuffer[head] = null;
        head++;
        if (head == elementsOfBuffer.length) {
            head = 0;
        }
        notifyAll();
        return value;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementsOfBuffer);
    }
}