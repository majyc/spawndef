package com.resurgence.spawndef;

import static org.junit.Assert.assertEquals;

public class VersionCommandTest {

	/*
	 *     
	>>> v = Version()
    >>> print(v)
    1   0   Version 2
	 */
	
	@org.junit.Test
	public void noArgCommand() {
		VersionCommand v = new VersionCommand();
		String expected = "1   0   Version 2";
		assertEquals(expected, v.toString());
	}

	@org.junit.Test
	public void timeIncrementCommand() {
		VersionCommand v = new VersionCommand(2);
		String expected = "2   0   Version 2";
		assertEquals(expected, v.toString());
	}
	
	@org.junit.Test
	public void stringConstructor() throws InvalidFormatException {
		String expected = "2   0   Version 2";
		VersionCommand v = new VersionCommand(expected);
		assertEquals(expected, v.toString());
	}
	
	@org.junit.Test
	public void stringConstructor2() throws InvalidFormatException {
		// Version 3 doesn't exist yet, but what if it did?
		String expected = "2   0   Version 3";
		VersionCommand v = new VersionCommand(expected);
		assertEquals(expected, v.toString());
	}
	
	@org.junit.Test
	public void copyConstructor() {
		VersionCommand v = new VersionCommand(2);
		String expected = "2   0   Version 2";
		assertEquals(expected, v.toString());
	}
		
}
