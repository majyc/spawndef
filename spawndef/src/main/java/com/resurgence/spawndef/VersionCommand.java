package com.resurgence.spawndef;

import java.util.ArrayList;

public class VersionCommand extends Command {
	/*
	 * 
	 """Version - command to set the demorecord version number
    This is a fixed format:
    1   0   Version 2
    
    >>> v = Version()
    >>> print(v)
    1   0   Version 2
    
    """
    def __init__(self):
        super().__init__(timeIncrement=1)
        self.name = "Version"
        self.data = [2]
	 */

	public static final String VERSION_COMMAND = "Version";
	private ArrayList<String> data;
	public VersionCommand(int timeIncrement) {
		super(timeIncrement);
		name = VERSION_COMMAND;
		data = new ArrayList<String>();
		data.add("2");
	}
	public VersionCommand() {
		this(1);
	}
	
	public VersionCommand(String expected) {
		this();
		super.parse(expected);
		String[] args = expected.split("\\s+");
		if (args.length == 4) {
			// passed a version number
			data.remove(0);
			data.add(args[3]);
		}
		
	}
	
	@Override
	public String dataToString() {
		String s = "";
		if (data != null) {
			for (Object iterable_element : data) {
				s += " " + iterable_element.toString();
			}
		}
		return s;
	}

}
