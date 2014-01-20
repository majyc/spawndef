package com.resurgence.spawndef;

import static org.junit.Assert.assertEquals;

public class PyrTest {

	
	@org.junit.Test
	public void move() {
		Pyr p = new Pyr("0", "90", "-0");
		String expected = "PYR   0   90   -0";
		assertEquals(expected, p.toString());
	}
	
	
}
