package com.epam.circbuffer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Anatolii_Hlazkov
 */
public abstract class CircularBuffer {

    protected Integer[] buffer = {-1, -1, -1, -1, -1};
    protected int writeIndex = 0;
    protected int readIndex = 0;
    protected int occupiedBuffers = 0;

    abstract void set(Integer value) throws InterruptedException;

    abstract Integer get() throws InterruptedException;

    public void displayState(String operation) {
        System.out.printf("%s%s%d)\n%s", operation,
                " (buffers occupied: ", occupiedBuffers, "buffers:  ");
        for (int value : buffer) {
            System.out.printf(" %2d  ", value);
        }
        System.out.print("\n         ");
        for (int i = 0; i < buffer.length; i++) {
            System.out.print("---- ");
        }

        System.out.print("\n         ");
        for (int i = 0; i < buffer.length; i++) {
            if (i == writeIndex && i == readIndex) {
                System.out.print(" WR");
            } else if (i == writeIndex) {
                System.out.print(" W   ");
            } else if (i == readIndex) {
                System.out.print("  R  ");
            } else {
                System.out.print("     ");
            }
        }
        System.out.println("\n");
    }
}
