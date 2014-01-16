package com.resurgence.spawndef;

import java.util.ArrayList;

public class Version extends Command {
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
	private ArrayList<Integer> data;
	public Version(int timeIncrement) {
		super(timeIncrement);
		name = VERSION_COMMAND;
		data = new ArrayList<Integer>();
		data.add(new Integer(2));
	}
	public Version() {
		this(1);
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
