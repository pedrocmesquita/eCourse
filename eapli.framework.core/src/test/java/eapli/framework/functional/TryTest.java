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

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

/**
 * @author Paulo Gandra de Sousa 2023.03.28
 */
class TryTest {

    @Test
    void ensureIfAnExceptionIsThrownALeftEitherIsCreated() {
        final Supplier<Boolean> s = () -> {
            throw new IllegalArgumentException();
        };
        final var result = new Try<>(s).get();
        assertEquals(IllegalArgumentException.class,
                result.leftValueOrElseThrow(IllegalStateException::new).getClass());
    }

    @Test
    void ensureIfAnExceptionIsNotThrownARightEitherIsCreated() {
        final Supplier<Boolean> s = () -> true;
        final var result = new Try<>(s).get();
        assertTrue(result.right().orElseThrow(IllegalStateException::new));
    }
}
