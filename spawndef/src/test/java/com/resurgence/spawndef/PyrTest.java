package com.resurgence.spawndef;

import static org.junit.Assert.assertEquals;

public class PyrTest {

	
	@org.junit.Test
	public void create() {
		Pyr p = new Pyr("0", "90", "-0");
		String expected = "PYR 0 90 -0";
		assertEquals(expected, p.toString());
	}
	
	@org.junit.Test
	public void createFromString() {
		String expected = "PYR 0 90 -0";
		Pyr p = new Pyr(expected);
		assertEquals(expected, p.toString());
	}
	
	@org.junit.Test
	public void copy() {
		String expected = "PYR   0   90   -0";  // liberal on input... extra whitespace doesn't matter
		Pyr p = new Pyr(expected);
		Pyr p2 = new Pyr(p);
		assertEquals(p2.toString(), p.toString());
	}
	
	
}
