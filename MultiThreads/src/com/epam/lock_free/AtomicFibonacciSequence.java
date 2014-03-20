/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.lock_free;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class AtomicFibonacciSequence {

    private static class FibonacciEntry {

        BigInteger previous;
        BigInteger current;

        FibonacciEntry() {
            previous = BigInteger.ZERO;
            current = BigInteger.ONE;
        }

        FibonacciEntry(BigInteger previous, BigInteger current) {
            this.previous = previous;
            this.current = current;
        }

        FibonacciEntry nextEntry() {
            return new FibonacciEntry(current, current.add(previous));
        }
    }

    private final AtomicReference<FibonacciEntry> entryHolder = new AtomicReference<>();

    public AtomicFibonacciSequence() {
        entryHolder.set(new FibonacciEntry());
    }

    public BigInteger next() {
        for (;;) {
            FibonacciEntry entry = entryHolder.get();
            FibonacciEntry nextEntry = entry.nextEntry();
            if (entryHolder.compareAndSet(entry, nextEntry)) {
                return nextEntry.current;
            }
        }
    }

}
