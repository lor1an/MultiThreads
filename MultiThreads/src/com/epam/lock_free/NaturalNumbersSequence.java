package com.epam.lock_free;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class NaturalNumbersSequence {

    private static class NaturalNumbersEntity {

        BigInteger number;
        NaturalNumbersEntity() {
            number = BigInteger.ZERO;
        }

        NaturalNumbersEntity(BigInteger previous, BigInteger current) {
            this.number = current;
        }

        NaturalNumbersEntity nextEntry() {
            return new NaturalNumbersEntity(number, number.add(BigInteger.ONE));
        }
    }

    private final AtomicReference<NaturalNumbersEntity> entryHolder = new AtomicReference<>();

    public NaturalNumbersSequence() {
        entryHolder.set(new NaturalNumbersEntity());
    }

    public BigInteger next() {
        for (;;) {
            NaturalNumbersEntity entry = entryHolder.get();
            NaturalNumbersEntity nextEntry = entry.nextEntry();
            if (entryHolder.compareAndSet(entry, nextEntry)) {
                return nextEntry.number;
            }
        }
    }
}
