package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.chart.TestUtilities;
import org.junit.*;

public class RangeMutantTest {


    /**
     * Confirm that the equals method can distinguish all the required fields.
     */
    @Test
    public void testEquals() {
        Range r1 = new Range(0.0, 1.0);
        Range r2 = new Range(0.0, 1.0);
        assertEquals(r1, r2);
        assertEquals(r2, r1);

        r1 = new Range(0.0, 1.0);
        r2 = new Range(0.5, 1.0);
        assertFalse(r1.equals(r2));

        r1 = new Range(0.0, 1.0);
        r2 = new Range(0.0, 2.0);
        assertFalse(r1.equals(r2));

        // a Range object cannot be equal to a different object type
        assertFalse(r1.equals(new Double(0.0)));
    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashCode() {
        Range a1 = new Range(1.0, 100.0);
        Range a2 = new Range(1.0, 100.0);
        assertEquals(a1.hashCode(), a2.hashCode());

        a1 = new Range(-100.0, 2.0);
        a2 = new Range(-100.0, 2.0);
        assertEquals(a1.hashCode(), a2.hashCode());
    }

    /**
     * Simple tests for the contains() method.
     */
    @Test
    public void testContains() {
        Range r1 = new Range(0.0, 1.0);
        assertFalse(r1.contains(Double.NaN));
        assertFalse(r1.contains(Double.NEGATIVE_INFINITY));
        assertFalse(r1.contains(-1.0));
        assertTrue(r1.contains(0.0));
        assertTrue(r1.contains(0.5));
        assertTrue(r1.contains(1.0));
        assertFalse(r1.contains(2.0));
        assertFalse(r1.contains(Double.POSITIVE_INFINITY));
    }

    /**
     * Tests the constrain() method for various values.
     */
    @Test
    public void testConstrain() {
        Range r1 = new Range(0.0, 1.0);

        double d = r1.constrain(0.5);
        assertEquals(0.5, d, 0.0000001);

        d = r1.constrain(0.0);
        assertEquals(0.0, d, 0.0000001);

        d = r1.constrain(1.0);
        assertEquals(1.0, d, 0.0000001);

        d = r1.constrain(-1.0);
        assertEquals(0.0, d, 0.0000001);

        d = r1.constrain(2.0);
        assertEquals(1.0, d, 0.0000001);

        d = r1.constrain(Double.POSITIVE_INFINITY);
        assertEquals(1.0, d, 0.0000001);

        d = r1.constrain(Double.NEGATIVE_INFINITY);
        assertEquals(0.0, d, 0.0000001);

        d = r1.constrain(Double.NaN);
        assertTrue(Double.isNaN(d));
    }

    /**
     * Simple tests for the intersects() method.
     */
    @Test
    public void testIntersects() {
        Range r1 = new Range(0.0, 1.0);
        assertFalse(r1.intersects(-2.0, -1.0));
        assertFalse(r1.intersects(-2.0, 0.0));
        assertTrue(r1.intersects(-2.0, 0.5));
        assertTrue(r1.intersects(-2.0, 1.0));
        assertTrue(r1.intersects(-2.0, 1.5));
        assertTrue(r1.intersects(0.0, 0.5));
        assertTrue(r1.intersects(0.0, 1.0));
        assertTrue(r1.intersects(0.0, 1.5));
        assertTrue(r1.intersects(0.5, 0.6));
        assertTrue(r1.intersects(0.5, 1.0));
        assertTrue(r1.intersects(0.5, 1.5));
        assertFalse(r1.intersects(1.0, 1.1));
        assertFalse(r1.intersects(1.5, 2.0));
    }

    /**
     * A simple test for the expand() method.
     */
    @Test
    public void testExpand() {
        Range r1 = new Range(0.0, 100.0);
        Range r2 = Range.expand(r1, 0.10, 0.10);
        assertEquals(-10.0, r2.getLowerBound(), 0.001);
        assertEquals(110.0, r2.getUpperBound(), 0.001);

        // Expand by 0% does not change the range
        r2 = Range.expand(r1, 0.0, 0.0);
        assertEquals(r1, r2);

        try {
            Range.expand(null, 0.1, 0.1);
            fail("Null value is accepted");
        } catch (Exception e) {
        }

        // Lower > upper: mid point is used
        r2 = Range.expand(r1, -0.8, -0.5);
        assertEquals(65.0, r2.getLowerBound(), 0.001);
        assertEquals(65.0, r2.getUpperBound(), 0.001);
    }

    /**
     * A simple test for the scale() method.
     */
    @Test
    public void testShift() {
        Range r1 = new Range(10.0, 20.0);
        Range r2 = Range.shift(r1, 20.0);
        assertEquals(30.0, r2.getLowerBound(), 0.001);
        assertEquals(40.0, r2.getUpperBound(), 0.001);

        r1 = new Range(0.0, 100.0);
        r2 = Range.shift(r1, -50.0, true);
        assertEquals(-50.0, r2.getLowerBound(), 0.001);
        assertEquals(50.0, r2.getUpperBound(), 0.001);

        r1 = new Range(-10.0, 20.0);
        r2 = Range.shift(r1, 20.0, true);
        assertEquals(10.0, r2.getLowerBound(), 0.001);
        assertEquals(40.0, r2.getUpperBound(), 0.001);

        r1 = new Range(-10.0, 20.0);
        r2 = Range.shift(r1, -30.0, true);
        assertEquals(-40.0, r2.getLowerBound(), 0.001);
        assertEquals(-10.0, r2.getUpperBound(), 0.001);

        r1 = new Range(-10.0, 20.0);
        r2 = Range.shift(r1, 20.0, false);
        assertEquals(0.0, r2.getLowerBound(), 0.001);
        assertEquals(40.0, r2.getUpperBound(), 0.001);

        r1 = new Range(-10.0, 20.0);
        r2 = Range.shift(r1, -30.0, false);
        assertEquals(-40.0, r2.getLowerBound(), 0.001);
        assertEquals(0.0, r2.getUpperBound(), 0.001);

        // Shifting with a delta of 0 does not change the range
        r2 = Range.shift(r1, 0.0);
        assertEquals(r1, r2);

        try {
            Range.shift(null, 0.1);
            fail("Null value is accepted");
        } catch (Exception e) {
        }
    }

    /**
     * A simple test for the scale() method.
     */
    @Test
    public void testScale() {
        Range r1 = new Range(0.0, 100.0);
        Range r2 = Range.scale(r1, 0.10);
        assertEquals(0.0, r2.getLowerBound(), 0.001);
        assertEquals(10.0, r2.getUpperBound(), 0.001);

        r1 = new Range(-10.0, 100.0);
        r2 = Range.scale(r1, 2.0);
        assertEquals(-20.0, r2.getLowerBound(), 0.001);
        assertEquals(200.0, r2.getUpperBound(), 0.001);

        // Scaling with a factor of 1 does not change the range
        r2 = Range.scale(r1, 1.0);
        assertEquals(r1, r2);

        try {
            Range.scale(null, 0.1);
            fail("Null value is accepted");
        } catch (Exception e) {
        }

        try {
            Range.scale(r1, -0.5);
            fail("Negative factor accepted");
        } catch (Exception e) {
        }
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        Range r1 = new Range(25.0, 133.42);
        Range r2 = (Range) TestUtilities.serialised(r1);
        assertEquals(r1, r2);
    }

    private static final double EPSILON = 0.0000000001;

    /**
     * Some checks for the combine method.
     */
//    Kills 223: 3
//    Gosh
    @Test
    public void testCombine() {
        Range r1 = new Range(1.0, 2.0);
        Range r2 = new Range(1.5, 2.5);

        assertNull(Range.combine(null, null));
        assertEquals(r1, Range.combine(r1, null));
        assertEquals(r2, Range.combine(null, r2));
        assertEquals(new Range(1.0, 2.5), Range.combine(r1, r2));

        Range r3 = new Range(Double.NaN, 1.3);
        Range rr = Range.combine(r1, r3);
        assertTrue(Double.isNaN(rr.getLowerBound()));
        assertEquals(2.0, rr.getUpperBound(), EPSILON);

        Range r4 = new Range(1.7, Double.NaN);
        rr = Range.combine(r4, r1);
        assertEquals(1.0, rr.getLowerBound(), EPSILON);
        assertTrue(Double.isNaN(rr.getUpperBound()));
//    }

    /**
     * Some checks for the combineIgnoringNaN() method.
     */
//    Kills on 242: 6, 
//    247: 2
//    248: 1, 4, 5, 7, 9
//        Justing
    @Test
    public void testCombineIgnoringNaN() {
        Range r1 = new Range(1.0, 2.0);
        Range r2 = new Range(1.5, 2.5);

        assertNull(Range.combineIgnoringNaN(null, null));
        assertEquals(r1, Range.combineIgnoringNaN(r1, null));
        assertEquals(r2, Range.combineIgnoringNaN(null, r2));
        assertEquals(new Range(1.0, 2.5), Range.combineIgnoringNaN(r1, r2));

        Range r3 = new Range(Double.NaN, 1.3);
        Range rr = Range.combineIgnoringNaN(r1, r3);
        assertEquals(1.0, rr.getLowerBound(), EPSILON);
        assertEquals(2.0, rr.getUpperBound(), EPSILON);

        Range r4 = new Range(1.7, Double.NaN);
        rr = Range.combineIgnoringNaN(r4, r1);
        assertEquals(1.0, rr.getLowerBound(), EPSILON);
        assertEquals(2.0, rr.getUpperBound(), EPSILON);
    }
    
//    Kills mutants 11, 12, 26, 27
    @Test
    public void testIsNaNRange() {
        assertTrue(new Range(Double.NaN, Double.NaN).isNaNRange());
        assertFalse(new Range(1.0, 2.0).isNaNRange());
        assertFalse(new Range(Double.NaN, 2.0).isNaNRange());
        assertFalse(new Range(1.0, Double.NaN).isNaNRange());
    }
}
