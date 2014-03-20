package com.rhjensen.sample.utilities.getopts;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Richard on 3/20/2014.
 *
 */
public class ArgsTest {
    @Test
    public void aNewArgsWithNoSchemaOrArgumentsIsValid() throws Exception {
        Args newArgs = new Args("", new String[0]);
        assertTrue(newArgs.isValid());
    }

    @Test
    public void testUsageForEmptyArgs() throws Exception {
        Args newArgs = new Args("", new String[0]);
        assertEquals("", newArgs.usage());
    }

    @Test
    public void testErrorMessageForEmptyArgs() throws Exception {
        Args newArgs = new Args("", new String[0]);
        assertEquals("", newArgs.errorMessage());
    }

    @Test
    public void testSimpleBooleanPresent() throws Exception {
        Args args = new Args("x", new String[]{"-x"});
        assertEquals(1, args.cardinality());
        assertEquals(true, args.getBoolean('x'));
    }

    @Test
    public void testUsageForSimpleBoolean() throws Exception {
        Args args = new Args("x", new String[]{"-x"});
        assertEquals("-[x]", args.usage());
    }

    @Test
    public void testErrorMessageForSimpleBoolean() throws Exception {
        Args args = new Args("x", new String[]{"-y"});
        assertEquals("Argument(s) -y unexpected.", args.errorMessage());
    }

    @Test
    public void testGetBooleanIsTrueWhenArgumentGiven() throws Exception {
        Args args = new Args("x", new String[]{"-x"});
        assertTrue(args.getBoolean('x'));
    }

    @Test
    public void testGetBooleanIsFalseWhenArgumentNotGiven() throws Exception {
        Args args = new Args("x", new String[0]);
        assertFalse(args.getBoolean('x'));
    }

    @Test
    public void testSimpleStringPresent() throws Exception {
        Args args = new Args("x*", new String[]{"-x", "param"});
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals("param", args.getString('x'));
    }

    @Test
    public void testErrorMessageWhenStringParameterIsMissing() throws Exception {
        Args args = new Args("x*", new String[]{"-x"});
        assertEquals("Could not find string parameter for -x.", args.errorMessage());
        assertEquals("", args.getString('x'));
    }

    @Test
    public void testSimpleIntPresent() throws Exception {
        Args args = new Args("x#", new String[]{"-x", "42"});
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals(42, args.getInt('x'));
    }
    @Test
    public void testErrorMessageForInvalidIntegerParameter() throws Exception {
        Args args = new Args("x#", new String[]{"-x", "Forty two"});
        assertEquals("Argument -x expects an integer but was 'Forty two'.", args.errorMessage());
    }
    @Test
    public void testMissingInteger() throws Exception {
        Args args = new Args("x#", new String[]{"-x"});
        assertEquals("Could not find integer parameter for -x.", args.errorMessage());
        assertEquals(0, args.getInt('x'));
    }

}
