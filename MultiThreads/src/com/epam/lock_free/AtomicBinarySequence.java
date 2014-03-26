package com.epam.lock_free;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class AtomicBinarySequence {

    private static class BinaryEntry {

        BigInteger current;
        final BigInteger TWO = new BigInteger("2");

        BinaryEntry() {
            current = BigInteger.ONE;
        }

        BinaryEntry(BigInteger current) {
            this.current = current;
        }

        BinaryEntry nextEntry() {
            return new BinaryEntry(current.multiply(TWO));
        }
    }

    private final AtomicReference<BinaryEntry> entryHolder = new AtomicReference<>();

    public AtomicBinarySequence() {
        entryHolder.set(new BinaryEntry());
    }

    public BigInteger next() {
        for (;;) {
            BinaryEntry entry = entryHolder.get();
            BinaryEntry nextEntry = entry.nextEntry();
            if (entryHolder.compareAndSet(entry, nextEntry)) {
                return nextEntry.current;
            }
        }
    }

}
