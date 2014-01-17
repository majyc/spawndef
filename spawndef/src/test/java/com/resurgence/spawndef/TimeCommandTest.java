package com.resurgence.spawndef;

import static org.junit.Assert.assertEquals;

public class TimeCommandTest {

	@org.junit.Test
	public void timeArgCommand() {
		TimeCommand t = new TimeCommand(10.00);
		String expected = "0   0   TIME 10.00";
		assertEquals(expected, t.toString());
	}

	@org.junit.Test
	public void enforceFormat() {
		TimeCommand t = new TimeCommand(11);
		String expected = "0   0   TIME 11.00";
		assertEquals(expected, t.toString());
	}

	@org.junit.Test
	public void copyConstructor() {
		TimeCommand t = new TimeCommand(11);
		TimeCommand t2 = new TimeCommand(t);
		assertEquals(t.toString(), t2.toString());
		String expected = "0   0   TIME 11.00";
		assertEquals(expected, t2.toString());
	}

}
