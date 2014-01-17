package com.resurgence.spawndef;

import static org.junit.Assert.assertEquals;

public class TimeTest {

	@org.junit.Test
	public void timeArgCommand() {
		Time t = new Time(10.00);
		String expected = "0   0   TIME 10.00";
		assertEquals(expected, t.toString());
	}

	@org.junit.Test
	public void enforceFormat() {
		Time t = new Time(11);
		String expected = "0   0   TIME 11.00";
		assertEquals(expected, t.toString());
	}

	@org.junit.Test
	public void copyConstructor() {
		Time t = new Time(11);
		Time t2 = new Time(t);
		assertEquals(t.toString(), t2.toString());
		String expected = "0   0   TIME 11.00";
		assertEquals(expected, t2.toString());
	}

}
