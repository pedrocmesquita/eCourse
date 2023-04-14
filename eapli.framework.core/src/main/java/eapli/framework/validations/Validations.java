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
package eapli.framework.validations;

import java.util.Collection;
import java.util.Objects;
import java.util.regex.Pattern;

import eapli.framework.actions.Action;
import eapli.framework.actions.Actions;
import eapli.framework.math.util.NumberPredicates;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.util.Utility;

/**
 * Utility class for executing actions based on validations.
 *
 * @author Paulo Gandra de Sousa
 */
@Utility
/* package */ class Validations {
    protected Validations() {
        // ensure utility
    }

    /**
     * Asserts the "trueness" of a function's result. If the function result is not
     * {@code true } then the specified action is performed
     *
     * @param test
     * @param consequence
     *            the action to execute if the test fails
     */
    public static void ensure(final boolean test, final Action consequence) {
        Actions.doIfNot(consequence, test);
    }

    /**
     * Checks if two values are equal and if not performs the specified action.
     *
     * @param a
     * @param b
     * @param consequence
     *            the action to execute of the test fails
     */
    public static void ensureAreEqual(final long a, final long b, final Action consequence) {
        ensure(areEqual(a, b), consequence);
    }

    static boolean areEqual(final long a, final long b) {
        return a == b;
    }

    /**
     * Checks if two objects are equal and if not performs the specified action.
     *
     * @param a
     * @param b
     * @param consequence
     *            the action to execute of the test fails
     */
    public static void ensureAreEqual(final Object a, final Object b, final Action consequence) {
        ensure(areEqual(a, b), consequence);
    }

    static boolean areEqual(final Object a, final Object b) {
        return Objects.equals(a, b);
    }

    /**
     * Checks if a string matches a regular expression.
     *
     * @param regex
     * @param arg
     * @param consequence
     *            the action to execute of the test fails
     */
    public static void ensureMatches(final Pattern regex, final String arg, final Action consequence) {
        ensure(matches(regex, arg), consequence);
    }

    static boolean matches(final Pattern regex, final String arg) {
        return regex.matcher(arg).find();
    }

    /**
     * Checks if all object references are not null.
     *
     * @param objects
     * @param consequence
     *            the action to execute of the test fails
     */
    public static void ensureNoneNull(final Action consequence, final Object... objects) {
        for (final Object each : objects) {
            Actions.doIfNot(consequence, nonNull(each));
        }
    }

    static boolean nonNull(final Object each) {
        return Objects.nonNull(each);
    }

    /**
     * Checks if a Collection is non null and contains at least an element.
     *
     * @param arg
     *            the collection to test
     * @param consequence
     *            the action to execute of the collection is null or empty
     */
    public static void ensureNonEmpty(final Collection<?> arg, final Action consequence) {
        ensure(isEmpty(arg), consequence);
    }

    static boolean isEmpty(final Collection<?> arg) {
        return arg != null && !arg.isEmpty();
    }

    /**
     * Checks if a string is neither null nor empty nor just white space.
     *
     * @param arg
     *            the string to test
     * @param consequence
     *            the action to execute of the test fails
     */
    public static void ensureNonEmpty(final String arg, final Action consequence) {
        ensure(nonEmpty(arg), consequence);
    }

    static boolean nonEmpty(final String arg) {
        return StringPredicates.isNonEmpty(arg);
    }

    /**
     * Checks if a value is positive, that is n > 0.
     *
     * @param arg
     * @param consequence
     *            the action to execute of the test fails
     */
    public static void ensureIsPositive(final long arg, final Action consequence) {
        ensure(isPositive(arg), consequence);
    }

    static boolean isPositive(final long arg) {
        return NumberPredicates.isPositive(arg);
    }

    /**
     * Checks if a value is non negative, that is n >= 0.
     *
     * @param arg
     * @param consequence
     *            the action to execute of the test fails
     */
    public static void ensureNonNegative(final long arg, final Action consequence) {
        ensure(isNonNegative(arg), consequence);
    }

    static boolean isNonNegative(final long arg) {
        return NumberPredicates.isNonNegative(arg);
    }

}