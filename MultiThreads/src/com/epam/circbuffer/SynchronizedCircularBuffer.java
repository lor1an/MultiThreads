package com.epam.circbuffer;

import java.util.Arrays;

/**
 *
 * @author lor1an
 */
public class SynchronizedCircularBuffer extends CircularBuffer {

    Integer elementsOfBuffer[];
    int head;
    int tail;

    @Override
    public synchronized void set(Integer value) throws InterruptedException {
        while (occupiedBuffers == buffer.length) {
            System.out.printf("All buffers full. Producer waits.\n");
            wait();
        }
        buffer[ writeIndex] = value; // set new buffer value
        // update circular write index
        writeIndex = (writeIndex + 1) % buffer.length;
        occupiedBuffers++; // one more buffer element is full
        displayState("Producer writes " + value);
        notifyAll();
    }

    @Override
    public synchronized Integer get() throws InterruptedException {
        int readValue; // initialize value read from buffer

        // wait until buffer has data, then read value
        // while no data to read, place thread in waiting state
        while (occupiedBuffers == 0) {
            System.out.printf("All buffers empty. Consumer waits.\n");
            wait();
        }
        readValue = buffer[ readIndex]; // read value from buffer

        // update circular read index
        readIndex = (readIndex + 1) % buffer.length;

        occupiedBuffers--; // one more buffer element is empty
        displayState("Consumer reads " + readValue);
        notifyAll();
        return readValue;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementsOfBuffer);
    }
}
