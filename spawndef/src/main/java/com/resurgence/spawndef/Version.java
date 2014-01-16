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

	public Version() {
		super(1);
		name = "Version";
		data = new ArrayList<Object>();
		data.add(new Integer(2));
	}

	public Version(int timeIncrement) {
		super(timeIncrement);
		name = "Version";
		data = new ArrayList<Object>();
		data.add(2);
	}

}
