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
package eapli.framework.representations.builders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import eapli.framework.money.domain.model.Money;
import eapli.framework.time.util.Calendars;

/**
 * @author Paulo Gandra de Sousa
 */
class JsonRepresentationBuilderRootCollectionTest {
    private JsonRepresentationBuilder subject;

    @BeforeEach
    void setUp() throws Exception {
        subject = new JsonRepresentationBuilder();
    }

    @Test
    void ensureCannotEndCollectionWithoutStartingCollection() {
        assertThrows(IllegalStateException.class, () -> subject.endCollection());
    }

    @Test
    void ensureCannotEndCollectionMoreThanOnce() {
        subject.startCollection("name").withElement(1).endCollection();
        assertThrows(IllegalStateException.class, () -> subject.endCollection());
    }

    @Test
    void ensureCannotAddPropertyAfterEndOfCollection() {
        subject.startCollection("name").withElement(1).endCollection();
        assertThrows(IllegalStateException.class, () -> subject.withProperty("one", 1));
    }

    @Test
    void ensureCannotAddCollectionAfterEndOfCollection() {
        subject.startCollection("name").withElement(1).endCollection();
        assertThrows(IllegalStateException.class, () -> subject.startCollection("two"));
    }

    @Test
    void ensureCannotAddObjectAfterEndOfCollection() {
        subject.startCollection("name").withElement(1).endCollection();
        assertThrows(IllegalStateException.class, () -> subject.startObject("two"));
    }

    @Test
    void ensureCannotAddElementAfterEndOfCollection() {
        subject.startCollection("name").withElement(1).endCollection();
        assertThrows(IllegalStateException.class, () -> subject.withElement(1));
    }

    @Test
    void ensureEmptyRootArray() {
        final String actual = subject.startCollection("name").endCollection().build();
        assertEquals("[]", actual);
    }

    @Test
    void ensureEmptyRootArrayAutoFinish() {
        final String actual = subject.startCollection("name").build();
        assertEquals("[]", actual);
    }

    @Test
    void ensureRootArrayWithNullLongElement() {
        final Long value = null;
        final String actual = subject.startCollection("name").withElement(value).endCollection().build();
        assertEquals("[null]", actual);
    }

    @Test
    void ensureRootArrayWith1PrimitiveIntElement() {
        final String actual = subject.startCollection("name").withElement(1).endCollection().build();
        assertEquals("[1]", actual);
    }

    @Test
    void ensureRootArrayWith2PrimitiveIntElements() {
        final String actual = subject.startCollection("name").withElement(1).withElement(2).endCollection().build();
        assertEquals("[1,2]", actual);
    }

    @Test
    void ensureRootArrayWith1StringElement() {
        final String actual = subject.startCollection("name").withElement("1").endCollection().build();
        assertEquals("[\"1\"]", actual);
    }

    @Test
    void ensureRootArrayWith2StringElements() {
        final String actual = subject.startCollection("name").withElement("1").withElement("2").endCollection().build();
        assertEquals("[\"1\",\"2\"]", actual);
    }

    @Test
    void ensureRootArrayWith1StringAnd1NativeElements() {
        final String actual = subject.startCollection("name").withElement("1").withElement(2).endCollection().build();
        assertEquals("[\"1\",2]", actual);
    }

    @Test
    void ensureRootArrayWithLotsOfElements() {
        final String expected = "[1,15,true,2.0,4.0,2,4,{\"amount\":50.0,\"currency\":\"EUR\"},\"abc\"]";
        final String actual = subject.startCollection("name").withElement(BigDecimal.ONE)
                .withElement(BigInteger.valueOf(15)).withElement(true).withElement(2.0D).withElement(4.0F)
                .withElement(2).withElement(4L).withElement(Money.euros(50)).withElement("abc").endCollection().build();

        System.out.println("ensureArrayWithLotsOfElements");
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);

        assertEquals(expected, actual);
    }

    @Test
    @Disabled("due to differences in time zones with bitbucket pipeline")
    void ensureRootArrayWithDateElement() {
        final String expected = "[\"2019-04-22T23:00Z\"]";
        final String actual = subject.startCollection("name").withElement(Calendars.of(2019, 4, 23)).endCollection()
                .build();

        System.out.println("ensureArrayWithDateElement");
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);

        assertEquals(expected, actual);
    }

    @Test
    void ensureRootCollectionOfOneCollection() {
        final String expected = "[[1,2,3]]";
        final String actual = subject.startCollection("name").startCollection("c1").withElement(1).withElement(2)
                .withElement(3).endCollection().endCollection().build();

        System.out.println("ensureRootCollectionOfOneCollection");
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);

        assertEquals(expected, actual);
    }

    @Test
    void ensureRootCollectionOfTwoCollections() {
        final String expected = "[[1,2],[3,4]]";
        final String actual = subject.startCollection("name").startCollection("c1").withElement(1).withElement(2)
                .endCollection().startCollection("c2").withElement(3).withElement(4).endCollection().endCollection()
                .build();

        System.out.println("ensureRootCollectionOfTwoCollections");
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);

        assertEquals(expected, actual);
    }

    @Test
    void ensureRootPolymorphicCollection() {
        final String expected = "[[1,2],\"a\",[3,4]]";
        final String actual = subject.startCollection("name").startCollection("c1").withElement(1).withElement(2)
                .endCollection().withElement("a").startCollection("c2").withElement(3).withElement(4).endCollection()
                .endCollection().build();

        System.out.println("ensureRootPolymorphicCollection");
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);

        assertEquals(expected, actual);
    }

}