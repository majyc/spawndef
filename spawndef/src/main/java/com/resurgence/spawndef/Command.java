package com.resurgence.spawndef;

import java.util.Scanner;

/*
 * class Command:
    """
    >>> c = Command()
    >>> print(c)
    0   0
    """
    def __init__(self, timeIncrement=None):
        if timeIncrement == None:
            self.timeIncrement = 0
        else:
            self.timeIncrement = timeIncrement
        self.entity = 0
        self.name = ""
        self.data = []
    def __str__(self):
        value = '{0}   {1}'.format(self.timeIncrement, self.entity)
        if self.name:
            value = value + '   ' + self.name
        for item in self.data:
            value = value + ' ' + str(item)
        return value
 */

public class Command {

	protected int timeIncrement;
	protected String entity; // usually an int, but can be a string like "CAM" for the camera

	public Command() {
		timeIncrement = 0;
		entity = "0"; 
		name = "COMMAND";
	}
	
	public Command(int timeIncrement) {
		this();
		this.timeIncrement = timeIncrement;
	}

	// can't really throw EmptyElementException unless something has gone horribly wrong, but that's Java for you
	public Command(Command c) throws InvalidFormatException {
		this(c.toString());
	}
	
	public Command(String string) throws InvalidFormatException {
		this();
		parse(string);
	}

	protected void parse(String string) throws InvalidFormatException {
		string = string.trim();
		// new Java 7 Automatic Resource Management lets you declare a scope and ensure that resources opened for that scope
		// always get close() called as if there was a finally block
		try (Scanner scanner = new Scanner(string)) {
			if (scanner.hasNext()) {
				timeIncrement = Integer.parseInt(scanner.next());
			}
			if (scanner.hasNext()) {
				entity = scanner.next();
			}
			if (scanner.hasNext()) {
				name = scanner.next();
			}
		}		
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected String name;

	public int getTimeIncrement() {
		return timeIncrement;
	}
	
	public void setTimeIncrement(String timeIncrement) {
		this.timeIncrement = Integer.parseInt(timeIncrement);
	}

	public String dataToString() {
		return "";
	}
	
	@Override
	public String toString() {
		String s = String.format("%1$s   %2$s", timeIncrement, entity);
		if (name.length() > 0) s += "   " + name;
		s += dataToString();
		return s;
	}

	public void setTimeIncrement(int timeIncrement) {
		this.timeIncrement = timeIncrement;
	}

}
