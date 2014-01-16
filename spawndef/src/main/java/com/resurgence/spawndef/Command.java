package com.resurgence.spawndef;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

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

	public Command() {
		timeIncrement = 0;
		entity = "0"; 
		name = "";
	}
	
	public Command(int timeIncrement) {
		this();
		this.timeIncrement = timeIncrement;
	}

	public int getTimeIncrement() {
		return timeIncrement;
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
