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
package eapli.framework.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import eapli.framework.actions.Actions;

/**
 *
 * @author Paulo Gandra de Sousa
 *
 */
class EitherEmptySpecialCaseTest {

    private <L, R> Either<L, R> empty() {
        return new EmptyEither<>();
    }

    @Test
    void ensureConsumeEmptyDoesNothing() {
        empty().consume(x -> Actions.throwState("This should never happen"),
                y -> Actions.throwState("This should never happen"));
        // it will fall thru as neither action should be invoked
        assertTrue(true);
    }

    @Test
    void ensureMappingAnEmptyEitherReturnsEmpty() {
        assertTrue(empty().map(x -> x, y -> y) instanceof EmptyEither);
    }

    @Test
    void ensureFilteringEmptyIsEmpty() {
        assertTrue(empty().filter(x -> true, y -> true) instanceof EmptyEither);
    }

    @Test
    void ensureFoldingOnEmptyEitherReturnsDefault() {
        final Either<String, Integer> subject = empty();
        final int DEFAULT = 1234;
        final int actual = subject.fold((x, y) -> x + y.length(), (x, y) -> x + y, DEFAULT);
        assertEquals(DEFAULT, actual);
    }

    @Test
    void ensureSwapingAnEmptyReturnsAnEmpty() {
        assertTrue(empty().swap() instanceof EmptyEither);
    }
}
