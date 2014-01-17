package com.resurgence.spawndef;

import static org.junit.Assert.*;

public class PosTest {

	/*
	 *    
	>>> p = Pos("CAM", -679.100287, 220.000000, -678.694545)
    >>> print(p)
    0   CAM   POS -679.100287 220.000000 -678.694545
    >>> p = Pos(33, 133.326782, 168.000000, -646.427490)
    >>> print(p)
    0   33   POS 133.326782 168.000000 -646.427490
    
	 */
	@org.junit.Test
	public void camCommand() {
		Pos p = new Pos("CAM", "-679.100287", "220.000000", "-678.694545");
		String expected = "0   CAM   POS -679.100287 220.000000 -678.694545";
		assertEquals(expected, p.toString());
	}

	@org.junit.Test
	public void entityCommand() {
		Pos p = new Pos("33", "133.326782", "168.000000", "-646.427490");
		String expected = "0   33   POS 133.326782 168.000000 -646.427490";
		assertEquals(expected, p.toString());
	}

	@org.junit.Test
	public void ensureFormat() {
		Pos p = new Pos("33", "133.326782", "168.0", "-646.427490");
		String expected = "0   33   POS 133.326782 168.000000 -646.427490";
		assertEquals(expected, p.toString());
	}

	@org.junit.Test
	public void formatZero() {
		Pos p = new Pos("33", "133.326782", "0.0", "-646.427490");
		String expected = "0   33   POS 133.326782 0 -646.427490";
		assertEquals(expected, p.toString());
	}

	@org.junit.Test
	public void copyConstructor() {
		Pos p = new Pos("33", "133.326782", "0.0", "-646.427490");
		Pos p2 = new Pos(p);
		String expected = "0   33   POS 133.326782 0 -646.427490";
		assertEquals("Matches expected", expected, p2.toString());
		assertEquals("Pos objects match", p.toString(), p2.toString());
		p.setX("11");
		assertNotEquals("Pos objects no longer match", p.toString(), p2.toString());
	}
	
	/*
	 * 
	 *    
	>>> p = Pos(33, 133.326782, 168.000000, -646.427490)
    >>> print(p)
    0   33   POS 133.326782 168.000000 -646.427490
    >>> p2 = p + (10, 20, -30)
    >>> print(p2)
    0   33   POS 143.326782 188.000000 -676.427490
    >>> print(p + North(10))  # North is lower Y
    0   33   POS 133.326782 158.000000 -646.427490
    >>> print(p + South(10))  # South is higher Y
    0   33   POS 133.326782 178.000000 -646.427490
    >>> print(p + West(10))   # West is higher X
    0   33   POS 143.326782 168.000000 -646.427490
    >>> print(p + East(10))   # East is lower X
    0   33   POS 123.326782 168.000000 -646.427490
    >>> print(p + NorthEast(10, 90))
    0   33   POS 123.326782 168.000000 -646.427490
    >>> p3 = Pos("33", 10, 20, -30)
    >>> print(p + p3)
    0   33   POS 143.326782 188.000000 -676.427490
	 */
	
	@org.junit.Test
	public void move() {
		Pos p = new Pos("33", "133.326782", "168.000000", "-646.427490");
		p.move(10, 20, -30);
		String expected = "0   33   POS 143.326782 188.000000 -676.427490";
		assertEquals(expected, p.toString());
	}
	
	@org.junit.Test
	public void moveNorth() {
		Pos p = new Pos("33", "133.326782", "168.000000", "-646.427490");
		Pos p2 = new Pos(p);
		p.move(COORD.Y, -10);
		String expected = "0   33   POS 133.326782 158.000000 -646.427490";
		assertEquals(expected, p.toString());
		p2.moveDirection(COMPASS.NORTH, 10);
		String expected2 = "0   33   POS 133.326782 158.000000 -646.427490";
		assertEquals(expected2, p2.toString());
		assertEquals(p.toString(), p2.toString());
	}
	
	@org.junit.Test
	public void moveSouth() {
		Pos p = new Pos("33", "133.326782", "168.000000", "-646.427490");
		Pos p2 = new Pos(p);
		p.move(COORD.Y, 10);
		String expected = "0   33   POS 133.326782 178.000000 -646.427490";
		assertEquals(expected, p.toString());
		p2.moveDirection(COMPASS.SOUTH, 10);
		assertEquals(expected, p2.toString());
		assertEquals(p.toString(), p2.toString());
	}

	@org.junit.Test
	public void moveWest() {
		Pos p = new Pos("33", "133.326782", "168.000000", "-646.427490");
		Pos p2 = new Pos(p);
		p.move(COORD.X, 10);
		String expected = "0   33   POS 143.326782 168.000000 -646.427490";
		assertEquals(expected, p.toString());
		p2.moveDirection(COMPASS.WEST, 10);
		assertEquals(expected, p2.toString());
		assertEquals(p.toString(), p2.toString());
	}

	@org.junit.Test
	public void moveEast() {
		Pos p = new Pos("33", "133.326782", "168.000000", "-646.427490");
		Pos p2 = new Pos(p);
		p.move(COORD.X, -10);
		String expected = "0   33   POS 123.326782 168.000000 -646.427490";
		assertEquals(expected, p.toString());
		p2.moveDirection(COMPASS.EAST, 10);
		assertEquals(expected, p2.toString());
		assertEquals(p.toString(), p2.toString());
	}

	@org.junit.Test
	public void moveAngle() {
		Pos p = new Pos("33", "133.326782", "168.000000", "-646.427490");
		p.moveAtAngle(90, 10);
		String expected =  "0   33   POS 123.326782 168.000000 -646.427490";
		assertEquals(expected, p.toString());
	}

	@org.junit.Test
	public void moveNorthEast() {
		Pos original = new Pos("33", "133.326782", "168.000000", "-646.427490");
		Pos modified = new Pos(original);
		modified.moveDirection(COMPASS.NORTHEAST, 10);
		// should be more north (lower Y) and more east (lower X)
		assertTrue("Modified position is more east (lower X)", modified.getX() < original.getX());
		assertTrue("Modified position is more north (lower Y)", modified.getY() < original.getY());
		String expected =  "0   33   POS 126.255714 160.928932 -646.427490";
		assertEquals(expected, modified.toString());
	}



	
}
