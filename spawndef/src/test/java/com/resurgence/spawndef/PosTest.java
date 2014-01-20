package com.resurgence.spawndef;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PosTest {

	/*
	 * 
	 *    
	>>> p = Pos(33, 133.326782, 168.000000, -646.427490)
    >>> print(p)
    POS 133.326782 168.000000 -646.427490
    >>> p2 = p + (10, 20, -30)
    >>> print(p2)
    POS 143.326782 188.000000 -676.427490
    >>> print(p + North(10))  # North is lower Y
    POS 133.326782 158.000000 -646.427490
    >>> print(p + South(10))  # South is higher Y
    POS 133.326782 178.000000 -646.427490
    >>> print(p + West(10))   # West is higher X
    POS 143.326782 168.000000 -646.427490
    >>> print(p + East(10))   # East is lower X
    POS 123.326782 168.000000 -646.427490
    >>> print(p + NorthEast(10, 90))
    POS 123.326782 168.000000 -646.427490
    >>> p3 = Pos("33", 10, 20, -30)
    >>> print(p + p3)
    POS 143.326782 188.000000 -676.427490
    
    This version is liberal in what it accepts... any double will do, but will omit trailing zeroes 
    this better matches Leandro's examples and what people tend to do when creating the definitions by hand
	 */
	
	@org.junit.Test
	public void move() {
		Pos p = new Pos("133.326782", "168.000000", "-646.427490");
		p.move(10, 20, -30);
		String expected = "Pos 143.326782 188 -676.42749";
		assertEquals(expected, p.toString());
	}
	
	@org.junit.Test
	public void moveNorth() {
		Pos p = new Pos("133.326782", "168.000000", "-646.427490");
		Pos p2 = new Pos(p);
		p.move(COORD.Y, -10);
		String expected = "Pos 133.326782 158 -646.42749";
		assertEquals(expected, p.toString());
		p2.moveDirection(COMPASS.NORTH, 10);
		String expected2 = "Pos 133.326782 158 -646.42749";
		assertEquals(expected2, p2.toString());
		assertEquals(p.toString(), p2.toString());
	}
	
	@org.junit.Test
	public void moveSouth() {
		Pos p = new Pos("133.326782", "168.000000", "-646.427490");
		Pos p2 = new Pos(p);
		p.move(COORD.Y, 10);
		String expected = "Pos 133.326782 178 -646.42749";
		assertEquals(expected, p.toString());
		p2.moveDirection(COMPASS.SOUTH, 10);
		assertEquals(expected, p2.toString());
		assertEquals(p.toString(), p2.toString());
	}

	@org.junit.Test
	public void moveWest() {
		Pos p = new Pos("133.326782", "168.000000", "-646.427490");
		Pos p2 = new Pos(p);
		p.move(COORD.X, 10);
		String expected = "Pos 143.326782 168 -646.42749";
		assertEquals(expected, p.toString());
		p2.moveDirection(COMPASS.WEST, 10);
		assertEquals(expected, p2.toString());
		assertEquals(p.toString(), p2.toString());
	}

	@org.junit.Test
	public void moveEast() {
		Pos p = new Pos("133.326782", "168.000000", "-646.427490");
		Pos p2 = new Pos(p);
		p.move(COORD.X, -10);
		String expected = "Pos 123.326782 168 -646.42749";
		assertEquals(expected, p.toString());
		p2.moveDirection(COMPASS.EAST, 10);
		assertEquals(expected, p2.toString());
		assertEquals(p.toString(), p2.toString());
	}

	@org.junit.Test
	public void moveAngle() {
		Pos p = new Pos("133.326782", "168.000000", "-646.427490");
		p.moveAtAngle(90, 10);
		String expected =  "Pos 123.326782 168 -646.42749";
		assertEquals(expected, p.toString());
	}

	@org.junit.Test
	public void moveNorthEast() {
		Pos original = new Pos("133.326782", "168", "-646.42749");
		Pos modified = new Pos(original);
		modified.moveDirection(COMPASS.NORTHEAST, 10);
		// should be more north (lower Y) and more east (lower X)
		assertTrue("Modified position is more east (lower X)", modified.getX() < original.getX());
		assertTrue("Modified position is more north (lower Y)", modified.getY() < original.getY());
		String expected =  "Pos 126.255714 160.928932 -646.42749";
		assertEquals(expected, modified.toString());
	}

}
