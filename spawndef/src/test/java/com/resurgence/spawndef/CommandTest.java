package com.resurgence.spawndef;

import static org.junit.Assert.*;

public class CommandTest {

	/*
	 *   
	>>> c = Command()
    >>> print(c)
    0   0   COMMAND
	 */
	@org.junit.Test
	public void noArgCommand() {
		Command c = new Command();
		String expected = "0   0   COMMAND";
		assertEquals(expected, c.toString());
	}
	
	@org.junit.Test
	public void copyCommand() {
		Command c = new Command(1);
		String expected = "1   0   COMMAND";
		assertEquals(expected, c.toString());
		Command c2;
		c2 = new Command(c);
		assertEquals(c.toString(), c2.toString());
	}
	
	@org.junit.Test
	public void stringConstructor() {
		Command c = new Command("1   0   COMMAND");
		String expected = "1   0   COMMAND";
		assertEquals(expected, c.toString());
	}
	
	
}
