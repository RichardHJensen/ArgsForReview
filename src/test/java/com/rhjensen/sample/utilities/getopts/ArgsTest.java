package com.rhjensen.sample.utilities.getopts;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
}
