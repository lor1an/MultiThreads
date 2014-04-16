package com.epam.lock_free;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class BinarySequence {

    private final AtomicReference<BinaryEntry> entry = new AtomicReference<>();

    public BinarySequence() {
        entry.set(new BinaryEntry());
    }

    public BigInteger next() {
        for (;;) {
            BinaryEntry binEntry = entry.get();
            BinaryEntry nextEntry = binEntry.nextEntry();
            if (entry.compareAndSet(binEntry, nextEntry)) {
                return nextEntry.current;
            }
        }
    }

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

}
