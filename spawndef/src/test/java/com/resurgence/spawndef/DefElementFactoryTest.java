package com.resurgence.spawndef;

import static org.junit.Assert.assertEquals;

public class DefElementFactoryTest {

	@org.junit.Test
	public void createPos() {
		String expected = "Pos 143.326782 188.000000 -676.427490";
		Pos p = (Pos) DefElementFactory.newInstance("Pos", expected);
		assertEquals("Round trip is successful", expected, p.toString());
		Pos p2 = new Pos("143.326782", "188.000000", "-676.427490");
		assertEquals("Creating via factory is equivalent to manually", p.toString(), p2.toString());
	}

	
}
