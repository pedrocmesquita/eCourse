/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.framework.domains.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Paulo Gandra Sousa
 */
class FromInfinityOpenIntervalTest extends IntervalTestBase {

    @BeforeAll
    static void setUpClass() {
        System.out.println("FromInfinityOpenRangeTest");
        final Interval.IntervalBuilder<Long> b = Interval.fromInfinity();
        subject = b.openTo(END);
    }

    @Test
    void ensureEndIsNotInRange() {
        System.out.println("ensureEndIsNotInRange");
        final Long target = Long.valueOf(END_VALUE);
        final boolean result = subject.includes(target);
        assertFalse(result, "end cannot be part of an open range");
    }

    @Test
    void ensureSmallValueIsIncluded() {
        System.out.println("ensureSmallValueIsIncluded");
        final Long target = Long.MIN_VALUE;
        final boolean result = subject.includes(target);
        assertTrue(result, "From infinity range must include any value smaller than end");
    }

    @Test
    void ensureIsFromInfinity() {
        System.out.println("ensureIsFromInfinity");
        final boolean result = subject.startsAtInfinity();
        assertTrue(result, "from infinity ranges must be from infinity");
    }

    @Test
    void ensureStringRepresentationIsOk() {
        final String expected = "]oo, " + END + "[";
        final String actual = subject.toString();
        assertEquals(expected, actual);
    }
}