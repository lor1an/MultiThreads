package com.epam.circbuffer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ConditionsCircularBuffer extends CircularBuffer {

    private final Lock accessLock = new ReentrantLock();
    private final Condition canWrite = accessLock.newCondition();
    private final Condition canRead = accessLock.newCondition();

    @Override
    public void set(Integer value) throws InterruptedException {
        accessLock.lock();
        try {
            while (occupiedBuffers == buffer.length) {
                System.out.printf("All buffers full. Producer waits.\n");
                canWrite.await();
            }

            buffer[ writeIndex] = value;

            writeIndex = (writeIndex + 1) % buffer.length;

            occupiedBuffers++;
            displayState("Producer writes " + value);
            canRead.signalAll();
        } finally {
            accessLock.unlock();
        }
    }

    @Override
    public Integer get() throws InterruptedException {
        int readValue;
        accessLock.lock();

        try {
            while (occupiedBuffers == 0) {
                System.out.printf("All buffers empty. Consumer waits.\n");
                canRead.await();
            }

            readValue = buffer[ readIndex];

            readIndex = (readIndex + 1) % buffer.length;

            occupiedBuffers--;
            displayState("Consumer reads " + readValue);
            canWrite.signalAll();
        } finally {
            accessLock.unlock();
        }

        return readValue;
    }

}
