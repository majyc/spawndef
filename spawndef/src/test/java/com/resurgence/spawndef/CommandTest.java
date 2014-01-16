package com.resurgence.spawndef;

import static org.junit.Assert.*;

public class CommandTest {

	/*
	 *   
	>>> c = Command()
    >>> print(c)
    0   0
	 */
	@org.junit.Test
	public void noArgCommand() {
		Command c = new Command();
		String expected = "0   0";
		assertEquals(expected, c.toString());
	}
	
}
