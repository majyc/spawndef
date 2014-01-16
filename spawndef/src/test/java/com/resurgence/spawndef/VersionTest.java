package com.resurgence.spawndef;

import static org.junit.Assert.assertEquals;

public class VersionTest {

	/*
	 *     
	>>> v = Version()
    >>> print(v)
    1   0   Version 2
	 */
	
	@org.junit.Test
	public void noArgCommand() {
		Version v = new Version();
		String expected = "1   0   Version 2";
		assertEquals(expected, v.toString());
	}

	@org.junit.Test
	public void timeIncrementCommand() {
		Version v = new Version(2);
		String expected = "2   0   Version 2";
		assertEquals(expected, v.toString());
	}

}
