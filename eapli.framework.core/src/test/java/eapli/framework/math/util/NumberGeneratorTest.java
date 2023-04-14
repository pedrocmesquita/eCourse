/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.framework.math.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import eapli.framework.collections.util.ArrayPredicates;

/**
 * Since the {@link NumberGenerator} class is based on random generation the tests cannot really
 * test the expected output. We just test some boundaries.
 * <p>
 * We know that the return will always be a random number because we are using a Random object,
 * but the point of TDD is to ignore the implementation details and work as an executable
 * specification.
 *
 * @author Paulo Gandra Sousa 28/05/2020
 *
 */
class NumberGeneratorTest {
    private static final Logger LOGGER = LogManager.getLogger(NumberGeneratorTest.class);

    @Test
    void ensureIntRandomness() {
        final Integer[] r = new Integer[] {
                NumberGenerator.anInt(),
                NumberGenerator.anInt(),
                NumberGenerator.anInt(),
                NumberGenerator.anInt()
        };

        assertTrue(ArrayPredicates.areAllDistinct(r));
    }

    @Test
    void ensureIntRandomnessLotsOfNumbers() {
        // of course even with true randomness, there is the possibility that 100 numbers will have
        // some repeated numbers...
        final int N = 100;
        final Integer[] r = new Integer[N];
        for (int i = 0; i < N; i++) {
            r[i] = NumberGenerator.anInt();
        }

        assertTrue(ArrayPredicates.areAllDistinct(r));
    }

    @Test
    void ensureIntRandomnessIsWithinBounds() {
        final int N = 100;
        final int BOUND = 500;
        final Integer[] r = new Integer[N];
        for (int i = 0; i < N; i++) {
            r[i] = NumberGenerator.anInt(BOUND);
        }

        assertTrue(ArrayPredicates.all(r, i -> i < BOUND));
    }

    @Test
    void ensureFloatRandomnessIsWithinBounds() {
        final int N = 100;
        final Float[] r = new Float[N];
        for (int i = 0; i < N; i++) {
            r[i] = NumberGenerator.aFloat();
        }

        assertTrue(ArrayPredicates.all(r, i -> i >= 0.0 && i <= 1.0));
    }

    @Test
    void ensureBytesHasRightSize() {
        final int N = 12;

        ensure1BytesHasRightSize(N);
        ensure1BytesHasRightSize(N + 1);
        ensure1BytesHasRightSize(N * 2);
        ensure1BytesHasRightSize(N * 3);
    }

    private void ensure1BytesHasRightSize(final int N) {
        final byte[] r1 = NumberGenerator.nBytes(N);
        LOGGER.debug("Generated bytes »» {} ««", r1);
        assertEquals(N, r1.length);
    }

    @Test
    void ensureHexHasRightSize() {
        final int N = 12;

        ensure1HexHasRightSize(N);
        ensure1HexHasRightSize(N + 1);
        ensure1HexHasRightSize(N * 2);
        ensure1HexHasRightSize(N * 3);
    }

    private void ensure1HexHasRightSize(final int N) {
        final String r1 = NumberGenerator.anHex(N);
        LOGGER.debug("Generated hex »» {} ««", r1);
        assertEquals(N, r1.length());
    }
}
